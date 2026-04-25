package com.yinnnh.pojo.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CartVO {
    // 购物车字段
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer quantity; // 购买数量
    private Integer selected; // 是否选中 1=选中 0=未选中

    // 图书信息（联查出来的）
    private String title;    // 书名
    private String author;   // 作者
    private String cover;   // 封面
    private BigDecimal price; // 价格
    private Integer stock; // 库存
}