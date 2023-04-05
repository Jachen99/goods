package com.goods.common.model.business;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Table(name = "biz_product")
public class Product {

    @Id
    private Long id;

    private String pNum;

    private String name;

    private String model;

    private String unit;

    private String remark;

    private Integer sort;

    private Date createTime;

    private Date modifiedTime;

    @NotNull(message = "分类不能为空")
    private Long[] categorys;

    @NotNull(message = "分类不能为空")
    private Long[] categoryKeys;

    private Long oneCategoryId;

    private Long twoCategoryId;

    private Long threeCategoryId;

    private String imageUrl;

    private Integer status;

}
