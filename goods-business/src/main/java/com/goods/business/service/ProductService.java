package com.goods.business.service;

import com.goods.common.vo.business.ProductStockVO;
import com.goods.common.vo.business.ProductVO;

import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/4 15:09
 */
public interface ProductService {
    /**
     * 查询物资资料list
     * @param productVO
     * @return
     */
    List<ProductVO> findProductList(ProductVO productVO);

    /**
     * 增加 物资资料
     * @param productVO
     */
    void add(ProductVO productVO);

    /**
     * 根据id获取物资资料
     * @param id
     * @return
     */
    ProductVO findProductById(Long id);

    /**
     * 更新物资资料
     * @param productVO
     */
    void update(ProductVO productVO);

    /**
     * 放入回收站
     * @param id
     */
    void remove(Long id);

    /**
     * 回收站回滚
     * @param id
     */
    void back(Long id);

    /**
     * 删除物资资料
     * @param id
     */
    void delete(Long id);

    /**
     * 获取物资库存信息
     * @return
     */
    List<ProductStockVO> findProductStocks(ProductVO productVO);

    /**
     * 获取所有物资库存信息
     * @param productVO
     * @return
     */
    List<ProductStockVO> findAllStocks(ProductVO productVO);
}
