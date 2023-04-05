package com.goods.business.converter;

import com.goods.common.model.business.ProductStock;
import com.goods.common.vo.business.ProductStockVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/4 19:56
 */
public class ProductStockConverter {

    /**
     * 转换VO list
     * @param productStocks
     * @return
     */
    public static List<ProductStockVO> convertProductStockVOs(List<ProductStock> productStocks) {
        List<ProductStockVO> resultList = new ArrayList<>();
        // 判空
        if (productStocks != null && productStocks.size() > 0) {
            for (ProductStock productStock : productStocks) {
                ProductStockVO productStockVO = new ProductStockVO();
                BeanUtils.copyProperties(productStock, productStockVO);
                resultList.add(productStockVO);
            }
        }
        return resultList;
    }
}
