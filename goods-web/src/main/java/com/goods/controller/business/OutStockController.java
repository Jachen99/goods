package com.goods.controller.business;

import com.goods.business.service.OutStockService;
import com.goods.common.response.ResponseBean;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.OutStockVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 业务管理模块 - 发放记录接口
 *
 * @author JaChen
 * @date 2023/4/5 10:10
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/business/outStock")
public class OutStockController {

    @Autowired
    private OutStockService outStockService;


    


    /**
     *
     * @param pageNum
     * @param pageSize
     * @param outStockVO
     * @return
     */
    @GetMapping("/findOutStockList")
    public ResponseBean<PageVO> findOutStockList(@RequestParam(name = "pageNum") Integer pageNum,
                                         @RequestParam(name = "pageSize") Integer pageSize,
                                         OutStockVO outStockVO){
        List<OutStockVO> outStockVOS = outStockService.findOutStockList(outStockVO);
        if (outStockVOS == null) {
            return ResponseBean.success(new PageVO(0,null));
        }
        outStockVOS = ListPageUtils.page(outStockVOS,pageSize,pageNum);
        PageVO pageVO = new PageVO(outStockVOS.size(),outStockVOS);
        return ResponseBean.success(pageVO);
    }


}
