package com.yinnnh.pojo.dto;





import lombok.Data;

@Data
public class BookDTO {
    // 查询参数
    private Integer categoryId; // 分类ID
    private Integer page;   // 当前页，默认1
    private Integer limit; // 每页条数，默认10
    private String sort;        // 排序字段
    private String title;           // 书名
    private String author;          // 作者

    // 搜索关键词（新增，用于搜索接口）
    private String keyword;
}