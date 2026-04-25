package com.yinnnh.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookImage {
    private Long id;                // 主键ID
    private Long bookId;           // 图书ID（对应book_id）
    private String imageUrl;       // 图片地址（对应image_url）
    private Integer imageType;     // 图片类型（对应image_type）
    private Integer sort;          // 排序号
    private Integer deleted;       // 逻辑删除：0正常 1删除
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}