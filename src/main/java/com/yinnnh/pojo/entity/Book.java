package com.yinnnh.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;                // 图书ID（主键自增）
    private String title;           // 书名
    private String author;          // 作者
    private String cover;           // 封面图URL（和表中cover字段对应）
    private BigDecimal price;       // 价格（decimal(10,2)）
    private Integer sales;          // 销量（默认0，热门排序用）

    private Integer stock;  //库存
    private Integer sort;           // 排序号（默认0）
    private Integer deleted;        // 逻辑删除：0正常 1删除（默认0）
    private LocalDateTime createTime; // 创建时间（自动维护）
    private LocalDateTime updateTime; // 更新时间（自动维护）

    private Integer categoryId;
}