package com.yinnnh.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    // 当前页数据
    private List<T> records;
    // 总条数
    private Long total;
    // 每页大小
    private Integer size;
}