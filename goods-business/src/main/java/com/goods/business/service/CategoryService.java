package com.goods.business.service;

import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;

import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/3 10:13
 */
public interface CategoryService {

    /**
     * 分页获取所有物资类别
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ProductCategoryTreeNodeVO> getCategoryTreeList();

    /**
     * 添加物资类别
     * @param productCategoryVO
     */
    void addParentCategory(ProductCategoryVO productCategoryVO);

    /**
     * 查询父分类
     * @return
     */
    List<ProductCategoryTreeNodeVO> getParentCategory();

    /**
     * 根据id查询类别信息
     * @param id
     * @return
     */
    ProductCategoryVO editParentCategory(Long id);

    /**
     * 更新分类信息
     * @param productCategoryVO
     */
    void updateParentCategory(ProductCategoryVO productCategoryVO);

    /**
     * 删除分类
     * @param id
     */
    void deleteParentCategory(Long id);
}
