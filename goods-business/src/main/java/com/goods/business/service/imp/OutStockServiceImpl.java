package com.goods.business.service.imp;

import com.goods.business.converter.OutStockConverter;
import com.goods.business.mapper.ConsumerMapper;
import com.goods.business.mapper.OutStockMapper;
import com.goods.business.service.OutStockService;
import com.goods.common.model.business.Consumer;
import com.goods.common.model.business.OutStock;
import com.goods.common.vo.business.OutStockVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/5 10:12
 */
@Service
@SuppressWarnings("all")
public class OutStockServiceImpl implements OutStockService {
    @Autowired
    private OutStockMapper outStockMapper;
    @Autowired
    private ConsumerMapper consumerMapper;
    @Override
    public List<OutStockVO> findOutStockList(OutStockVO outStockVO) {
        if (outStockVO == null) {
            return null;
        }
        OutStock out = OutStockConverter.convertOutStock(outStockVO);
        // 根据发放单的状态查询biz_consumer id
        Example example = new Example(OutStock.class);
        example.createCriteria().andEqualTo("status", out.getStatus());
        List<OutStock> outStockList = outStockMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(outStockList)) {
            // 创建结果集
            List<OutStockVO> outStockVOList = new ArrayList<>();
            for (OutStock outStock: outStockList) {
                // 获取关联表id
                Long consumerId = outStock.getConsumerId();
                // 根据id查询biz_consumer信息
                Consumer consumer = consumerMapper.selectByPrimaryKey(consumerId);
                if (consumer != null) {
                    // 创建结果集中对象
                    OutStockVO result = new OutStockVO();
                    // 封装数据
                    result.setConsumerId(outStock.getId());
                    result.setOutNum(outStock.getOutNum());
                    result.setType(outStock.getType());
                    result.setPriority(outStock.getPriority());
                    result.setName(consumer.getName());
                    result.setProductNumber(outStock.getProductNumber());
                    result.setPhone(consumer.getPhone());
                    result.setStatus(outStock.getStatus());
                    result.setOperator(outStock.getOperator());
                    result.setCreateTime(outStock.getCreateTime());
                    outStockVOList.add(result);
                }
            }
            // 返回结果
            return outStockVOList;
        }
        return null;
    }
}
