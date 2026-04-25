package com.yinnnh.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer quantity;
    private Integer selected;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
}
