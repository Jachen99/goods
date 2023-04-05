package com.goods.controller.business;

import com.goods.business.service.SupplierService;
import com.goods.common.response.ResponseBean;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/3 13:52
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/business/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;



    /**
     * 根据id删除物资来源
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable("id") Long id) {
        supplierService.delete(id);
        return ResponseBean.success();
    }






    /**
     * 更新物资来源数据
     * @param supplierVO
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseBean update(@RequestBody SupplierVO supplierVO) {
        supplierService.update(supplierVO);
        return ResponseBean.success();
    }





    /**
     * 编辑 根据id获取数据
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable("id") Long id) {
        SupplierVO supplierVO = supplierService.get(id);
        return ResponseBean.success(supplierVO);
    }






    /**
     * 新增物资来源
     * @param supplierVO
     * @return
     */
    @PostMapping("/add")
    public ResponseBean add(@RequestBody SupplierVO supplierVO) {
        supplierService.add(supplierVO);
        return ResponseBean.success();
    }



    /**
     * 获取物资来源分页列表
     * @param supplierVO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findSupplierList")
    public ResponseBean findSupplierList(@RequestParam(name = "pageNum") Integer pageNum,
                                         @RequestParam(name = "pageSize") Integer pageSize,
                                         SupplierVO supplierVO){
        // 查询物资来源信息
        List<SupplierVO> supplierVOList = supplierService.findSupplierList(supplierVO);
        // 判空
        if (supplierVOList == null) {
            return ResponseBean.success(new PageVO(0,null));
        }
        // 分页
        supplierVOList = ListPageUtils.page(supplierVOList, pageSize, pageNum);
        // 封装分页结果集
        PageVO pageVO = new PageVO(supplierVOList.size(), supplierVOList);
        // 响应数据
        return ResponseBean.success(pageVO);
    }

}
