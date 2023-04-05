package com.goods.business.converter;

import com.goods.common.model.business.Supplier;
import com.goods.common.vo.business.SupplierVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/3 14:36
 */
public class SupplierConverter {

    /**
     * 转换VO list
     * @param supplierList
     * @return
     */
    public static List<SupplierVO> convertSupplierList(List<Supplier> supplierList) {
        List<SupplierVO> resultList = new ArrayList<>();
        // 判空
        if (supplierList != null && supplierList.size() > 0) {
            for (Supplier supplier : supplierList) {
                SupplierVO supplierVO = new SupplierVO();
                BeanUtils.copyProperties(supplier, supplierVO);
                resultList.add(supplierVO);
            }
        }
        return resultList;
    }

    /**
     * 转换实体
     * @param supplierVO
     * @return
     */
    public static Supplier convertSupplier(SupplierVO supplierVO) {
        Supplier supplier = new Supplier();
        if (supplierVO == null) {
            return supplier;
        }
        BeanUtils.copyProperties(supplierVO, supplier);
        supplier.setCreateTime(new Date());
        return supplier;
    }
}
