package com.goods.business.converter;

import com.goods.common.model.business.ProductCategory;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/3 10:37
 */
public class CategoryConverter {


    /**
     * 转换VO list
     * @param categoryList
     * @return
     */
    public static List<ProductCategoryTreeNodeVO> convertCategoryVOList(List<ProductCategory> categoryList) {
        List<ProductCategoryTreeNodeVO> resultList = new ArrayList<>();
        // 判空
        if (categoryList != null && categoryList.size() > 0) {
            for (ProductCategory productCategory : categoryList) {
                ProductCategoryTreeNodeVO treeNodeVO = new ProductCategoryTreeNodeVO();
                BeanUtils.copyProperties(productCategory, treeNodeVO);
                resultList.add(treeNodeVO);
            }
        }
        return resultList;
    }

    /**
     * 转换VO
     * @param productCategory
     * @return
     */
    public static ProductCategoryVO convertCategoryVO(ProductCategory productCategory) {
        if (productCategory != null) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            BeanUtils.copyProperties(productCategory, productCategoryVO);
            return productCategoryVO;
        }
        return null;
    }
}
