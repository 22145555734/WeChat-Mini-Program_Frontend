package com.yinnnh.controller;


import com.yinnnh.common.Result;
import com.yinnnh.pojo.entity.Cart;
import com.yinnnh.pojo.vo.CartVO;
import com.yinnnh.service.CartService;
import com.yinnnh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public Result<List<CartVO>> getCartList() {
        // 1. 从登录上下文获取当前用户ID，前端不用传参
        Long userId = UserContext.getUserId();
        // 2. 调用Service查询
        List<CartVO> cartList = cartService.getCartList(userId);
        return Result.success(cartList);
    }
}
