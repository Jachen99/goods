package com.goods.controller.business;

import com.goods.business.service.ProductService;
import com.goods.common.response.ResponseBean;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.ProductStockVO;
import com.goods.common.vo.business.ProductVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/4 15:11
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/business/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * 获取所有库存信息  饼状图
     * @param pageNum
     * @param pageSize
     * @param productVO
     * @return
     */
    @GetMapping("/findAllStocks")
    public ResponseBean findAllStocks(@RequestParam(name = "pageNum") Integer pageNum,
                                      @RequestParam(name = "pageSize") Integer pageSize,
                                      ProductVO productVO) {
        List<ProductStockVO> productVOS = productService.findAllStocks(productVO);
        // 判空
        if (CollectionUtils.isEmpty(productVOS)) {
            return ResponseBean.success(new PageVO<>(0,null));
        }
        return ResponseBean.success(productVOS);
    }







    /**
     * 物资库存列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findProductStocks")
    public ResponseBean findProductStocks(@RequestParam(name = "pageNum") Integer pageNum,
                                          @RequestParam(name = "pageSize") Integer pageSize,
                                          ProductVO productVO) {
        List<ProductStockVO> productVOS = productService.findAllStocks(productVO);
        // 判空
        if (CollectionUtils.isEmpty(productVOS)) {
            return ResponseBean.success(new PageVO<>(0,null));
        }
        productVOS = ListPageUtils.page(productVOS,pageSize,pageNum);
        PageVO pageVO = new PageVO(productVOS.size(),productVOS);
        return ResponseBean.success(pageVO);
    }




    /**
     * 删除物资资料
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseBean.success();
    }





    /**
     * 回收站数据回滚
     * @param id
     * @return
     */
    @PutMapping("/back/{id}")
    public ResponseBean back(@PathVariable Long id) {
        productService.back(id);
        return ResponseBean.success();
    }




    /**
     * 放入回收站
     * @param id
     * @return
     */
    @PutMapping("/remove/{id}")
    public ResponseBean remove(@PathVariable Long id) {
        productService.remove(id);
        return ResponseBean.success();
    }




    /**
     * 更新物资资料
     * @param productVO
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseBean update(@RequestBody ProductVO productVO) {
        productService.update(productVO);
        return ResponseBean.success();
    }




    /**
     * 编辑
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) {
        ProductVO productVO = productService.findProductById(id);
        return ResponseBean.success(productVO);
    }





    /**
     * 添加物资资料
     * @param productVO
     * @return
     */
    @PostMapping("/add")
    public ResponseBean add(@RequestBody ProductVO productVO) {
        productService.add(productVO);
        return ResponseBean.success();
    }





    /**
     * 获取物资资料列表
     * @param pageNum
     * @param pageSize
     * @param productVO
     * @return
     */
    @GetMapping("/findProductList")
    public ResponseBean findProductList(@RequestParam(name = "pageNum") Integer pageNum,
                                        @RequestParam(name = "pageSize") Integer pageSize,
                                        ProductVO productVO){
        List<ProductVO> productList = productService.findProductList(productVO);
        // 判空
        if (CollectionUtils.isEmpty(productList)) {
            return ResponseBean.success(new PageVO<>(0,null));
        }
        productList = ListPageUtils.page(productList,pageSize,pageNum);
        PageVO pageVO = new PageVO(productList.size(),productList);
        return ResponseBean.success(pageVO);
    }

}
