package com.goods.business.service.imp;

import com.goods.business.converter.ProductConverter;
import com.goods.business.converter.ProductStockConverter;
import com.goods.business.mapper.ProductMapper;
import com.goods.business.mapper.ProductStockMapper;
import com.goods.business.service.ProductService;
import com.goods.common.model.business.Product;
import com.goods.common.model.business.ProductStock;
import com.goods.common.vo.business.ProductStockVO;
import com.goods.common.vo.business.ProductVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author JaChen
 * @date 2023/4/4 15:10
 */
@Service
@SuppressWarnings("all")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductStockMapper productStockMapper;


    @Override
    public List<ProductVO> findProductList(ProductVO productVO) {
        // 获取三级分类集合
        @NotNull(message = "分类不能为空")
        Long[] keys = productVO.getCategorys();
        // 不传三级分类查询
        if (keys == null || keys.length == 0){
            Example example = new Example(Product.class);
            example.createCriteria()
                    .andEqualTo("status", productVO.getStatus())
                    .andLike("name","%"+productVO.getName() + "%");
            List<Product> products = productMapper.selectByExample(example);
            if (products != null && products.size() > 0) {
                List<ProductVO> productVOS = ProductConverter.convertProductVOs(products);
                return productVOS;
            }
            return null;
        }
        // 校验 只能支持三级分类查询 只有二级一级都不行！
        if (keys.length > 0 && keys.length < 3)
            return null;
        // 全条件查询
        Example example = new Example(Product.class);
        example.createCriteria()
                .andEqualTo("status", productVO.getStatus())
                .andEqualTo("oneCategoryId", keys[0])
                .andEqualTo("twoCategoryId", keys[1])
                .andEqualTo("threeCategoryId", keys[2])
                .andLike("name","%"+productVO.getName() + "%");
        List<Product> products = productMapper.selectByExample(example);
        if (products != null && products.size() > 0) {
            List<ProductVO> productVOS = ProductConverter.convertProductVOs(products);
            return productVOS;
        }
        return null;
    }

    @Override
    public void add(ProductVO productVO) {
        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);
        product.setCreateTime(new Date());
        product.setStatus(0);
        product.setPNum(UUID.randomUUID().toString());
        @NotNull(message = "分类不能为空")
        Long[] keys = productVO.getCategoryKeys();
        product.setOneCategoryId(keys[0]);
        product.setTwoCategoryId(keys[1]);
        product.setThreeCategoryId(keys[2]);
        productMapper.insert(product);
    }

    @Override
    public ProductVO findProductById(Long id) {
        Product product = productMapper.selectByPrimaryKey(id);
        if (product != null) {
            ProductVO productVO = ProductConverter.convertProductVO(product);
            return productVO;
        }
        return null;
    }

    @Override
    public void update(ProductVO productVO) {
        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public void remove(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(1);
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public void back(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(0);
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public void delete(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ProductStockVO> findProductStocks(ProductVO productVO) {
        List<ProductStock> productStocks = productStockMapper.selectAll();
        List<ProductStockVO> result = new ArrayList<>();
        productStocks.stream().forEach(productStock -> {
            String pNum = productStock.getPNum();
            Example example = new Example(Product.class);
            example.createCriteria().andEqualTo("pNum", pNum);
            List<Product> products = productMapper.selectByExample(example);
            if (CollectionUtils.isNotEmpty(products)){
                products.stream().forEach(product -> {
                    ProductStockVO productStockVO = new ProductStockVO();
                    BeanUtils.copyProperties(product,productStockVO);
                    productStockVO.setStock(productStock.getStock());
                    result.add(productStockVO);
                });
            }
        });
        if (result != null && result.size() > 0) {
            return result;
        }
        return null;
    }

    @Override
    public List<ProductStockVO> findAllStocks(ProductVO productVO) {
        // 获取全部物资集合
        List<ProductVO> productList = this.findProductList(productVO);
        if (CollectionUtils.isNotEmpty(productList)){
            // 创建返回结果集
            ArrayList<ProductStockVO> stockVOS = new ArrayList<>();
            productList.stream().forEach(product -> {
                // 创建结果集中对象
                ProductStockVO productStockVO = new ProductStockVO();
                // 把product交给productStockVO管理
                BeanUtils.copyProperties(product, productStockVO);
                String pNum = productStockVO.getPNum();
                // 条件匹配物资库存信息
                Example example = new Example(Product.class);
                example.createCriteria().andEqualTo("pNum", pNum);
                List<ProductStock> stocks = productStockMapper.selectByExample(example);
                // 遍历获取stock属性
                if (CollectionUtils.isNotEmpty(stocks)) {
                    List<ProductStockVO> productStockVOS = ProductStockConverter.convertProductStockVOs(stocks);
                    productStockVOS.forEach(stockVO -> {
                        // 属性添加
                        productStockVO.setStock(stockVO.getStock());
                        // 添加结果集集合
                        stockVOS.add(productStockVO);
                    });
                }
            });
            return stockVOS;
        }
        return null;
    }


}
