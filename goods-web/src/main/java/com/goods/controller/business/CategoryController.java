package com.goods.controller.business;

import com.goods.business.service.CategoryService;
import com.goods.common.response.ResponseBean;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 业务管理模块 - 物资类别接口
 *
 * @author JaChen
 * @date 2023/4/3 10:06
 */
@Api(tags = "务管理模块-物资类别接口")
@RestController
@RequestMapping("/business/productCategory")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;



    @ApiOperation(value = "根据id删除分类")
    @DeleteMapping("/delete/{id}")
    public ResponseBean<String> deleteParentCategory(@PathVariable Long id) {
        categoryService.deleteParentCategory(id);
        return ResponseBean.success();
    }




    @ApiOperation(value = "更新分类")
    @PutMapping("/update/{id}")
    public ResponseBean<String> updateParentCategory(@RequestBody ProductCategoryVO productCategoryVO) {
        categoryService.updateParentCategory(productCategoryVO);
        return ResponseBean.success();
    }



    @ApiOperation(value = "根据id查询更新分类")
    @GetMapping("/edit/{id}")
    public ResponseBean<ProductCategoryVO> editParentCategory(@PathVariable Long id) {
        ProductCategoryVO productCategoryVO = categoryService.editParentCategory(id);
        return ResponseBean.success(productCategoryVO);
    }




    @ApiOperation(value = "添加物资类别")
    @PostMapping("/add")
    public ResponseBean<String> addParentCategory(@RequestBody ProductCategoryVO productCategoryVO) {
        // 调用service方法处理
        categoryService.addParentCategory(productCategoryVO);
        // 响应状态
        return ResponseBean.success();
    }


    @ApiOperation(value = "查询父分类")
    @GetMapping("/getParentCategoryTree")
    public ResponseBean<List<ProductCategoryTreeNodeVO>> getParentCategory() {
        List<ProductCategoryTreeNodeVO> parentCategoryList = categoryService.getParentCategory();
        return ResponseBean.success(parentCategoryList);
    }



    @ApiOperation(value = "分页获取所有物资类别")
    @GetMapping("/categoryTree")
    public ResponseBean<PageVO<ProductCategoryTreeNodeVO>> getListPage(Integer pageNum, Integer pageSize){
        // 调用service进行业务处理
        List<ProductCategoryTreeNodeVO> treeNodeVOList = categoryService.getCategoryTreeList();
        // 数据分页
        if (!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            treeNodeVOList = ListPageUtils.page(treeNodeVOList, pageSize, pageNum);
        }
        // 响应结果
        if (!CollectionUtils.isEmpty(treeNodeVOList)){
            return ResponseBean.success(new PageVO<>(treeNodeVOList.size(),treeNodeVOList));
        }
        return null;
    }























}
