package com.goods.business.service.imp;

import com.goods.business.converter.CategoryConverter;
import com.goods.business.mapper.CategoryMapper;
import com.goods.business.service.CategoryService;
import com.goods.common.model.business.ProductCategory;
import com.goods.common.utils.CategoryTreeBuilder;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 类别业务层
 *
 * @author JaChen
 * @date 2023/4/3 10:13
 */
@Service
@SuppressWarnings("all")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<ProductCategoryTreeNodeVO> getCategoryTreeList() {
        // 查询数据库 获取分类信息list
        List<ProductCategory> categoryList = categoryMapper.selectAll();
        // 判空
        if (categoryList == null) {
            return null;
        }
        // VO 转换
        List<ProductCategoryTreeNodeVO> categoryVOList = CategoryConverter.convertCategoryVOList(categoryList);
        // 建树
        List<ProductCategoryTreeNodeVO> treeNodeVOList = CategoryTreeBuilder.build(categoryVOList);
        return treeNodeVOList;
    }

    @Override
    public void addParentCategory(ProductCategoryVO productCategoryVO) {
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryVO,productCategory);
        productCategory.setCreateTime(new Date());
        productCategory.setModifiedTime(new Date());
        // 插入数据库
        categoryMapper.insert(productCategory);
    }

    @Override
    public List<ProductCategoryTreeNodeVO> getParentCategory() {

        // 查询数据库 获取分类信息list
        List<ProductCategory> categoryList = categoryMapper.selectAll();
        // 判空
        if (categoryList == null) {
            return null;
        }
        // VO 转换
        List<ProductCategoryTreeNodeVO> categoryVOList = CategoryConverter.convertCategoryVOList(categoryList);
        // 查询父分类
        List<ProductCategoryTreeNodeVO> treeNodeVOList = CategoryTreeBuilder.buildParent(categoryVOList);

        return treeNodeVOList;
    }

    @Override
    public ProductCategoryVO editParentCategory(Long id) {
        ProductCategory productCategory = categoryMapper.selectByPrimaryKey(id);
        if (productCategory != null) {
            // 转换VO
            ProductCategoryVO productCategoryVO = CategoryConverter.convertCategoryVO(productCategory);
            return productCategoryVO;
        }
        return null;
    }

    @Override
    public void updateParentCategory(ProductCategoryVO productCategoryVO) {
        // 转换VO
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryVO,productCategory);
        productCategory.setModifiedTime(new Date());
        // 根据主键更新数据
        categoryMapper.updateByPrimaryKey(productCategory);
    }

    @Override
    public void deleteParentCategory(Long id) {
        ProductCategory productCategory = categoryMapper.selectByPrimaryKey(id);
        if (productCategory != null) {
            categoryMapper.deleteByPrimaryKey(id);
        }
    }
}
