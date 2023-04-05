package com.goods.business.converter;

import com.goods.common.model.business.Product;
import com.goods.common.vo.business.ProductVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/4 15:07
 */
public class ProductConverter {

    /**
     * 转换实体
     * @param productVO
     * @return
     */
    public static Product convertProduct(ProductVO productVO) {
        Product product = new Product();
        if (productVO == null) {
            return product;
        }
        BeanUtils.copyProperties(productVO, product);
        product.setCreateTime(new Date());
        return product;
    }

    /**
     * 转换VO
     * @param products
     * @return
     */
    public static List<ProductVO> convertProductVOs(List<Product> products) {
        if (products != null && !products.isEmpty()) {
            List<ProductVO> productVOs = new ArrayList<>();
            for (Product product: products) {
                ProductVO productVO = new ProductVO();
                BeanUtils.copyProperties(product, productVO);
                productVOs.add(productVO);
            }
            return productVOs;
        }
        return null;
    }

    public static ProductVO convertProductVO(Product product) {
        if (product != null) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            return productVO;
        }
        return null;
    }
}
