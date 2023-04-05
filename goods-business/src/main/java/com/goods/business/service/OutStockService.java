package com.goods.business.service;

import com.goods.common.vo.business.OutStockVO;

import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/5 10:12
 */
public interface OutStockService {
    /**
     * 获取出口物资列表
     * @param outStockVO
     * @return
     */
    List<OutStockVO> findOutStockList(OutStockVO outStockVO);
}
