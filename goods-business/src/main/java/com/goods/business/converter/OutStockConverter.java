package com.goods.business.converter;

import com.goods.common.model.business.OutStock;
import com.goods.common.vo.business.OutStockVO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author JaChen
 * @date 2023/4/5 11:00
 */
public class OutStockConverter {




    /**
     * 转换实体
     * @param outStockVO
     * @return
     */
    public static OutStock convertOutStock(OutStockVO outStockVO) {
        OutStock outStock = new OutStock();
        if (outStockVO == null) {
            return outStock;
        }
        BeanUtils.copyProperties(outStockVO, outStock);
        outStock.setCreateTime(new Date());
        return outStock;
    }





    /**
     * 转化VO
     * @param outStock
     * @return
     */
    public static OutStockVO convertOutStockVO(OutStock outStock) {
        if (outStock != null) {
            OutStockVO outStockVO = new OutStockVO();
            BeanUtils.copyProperties(outStock, outStockVO);
            return outStockVO;
        }
        return null;
    }
}
