package com.yinnnh.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 商品分类实体类，对应 category 表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private String icon;
    private Long parentId;
    private Integer sort;
    private Integer deleted;
}