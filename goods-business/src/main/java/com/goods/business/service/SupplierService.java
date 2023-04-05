package com.goods.business.service;

import com.goods.common.vo.business.SupplierVO;

import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/3 13:55
 */
public interface SupplierService {
    /**
     * 获取物资来源分页列表
     * @param supplierVO
     * @return
     */
    List<SupplierVO> findSupplierList(SupplierVO supplierVO);

    /**
     * 新增物资来源
     * @param supplierVO
     */
    void add(SupplierVO supplierVO);

    /**
     * 根据id获取物资来源
     * @param id
     * @return
     */
    SupplierVO get(Long id);

    /**
     * 更新物资来源
     * @param supplierVO
     */
    void update(SupplierVO supplierVO);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
