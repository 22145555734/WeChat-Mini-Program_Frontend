package com.yinnnh.service.impl;

import com.yinnnh.common.ResultEnum;
import com.yinnnh.exception.BizException;
import com.yinnnh.mapper.CartMapper;
import com.yinnnh.pojo.vo.CartVO;
import com.yinnnh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<CartVO> getCartList(Long userId) {
        // 1. 校验用户登录态（兜底，拦截器已经做过，这里双重保险）
        if (userId == null) {
            throw new BizException(ResultEnum.UNAUTHORIZED);
        }
        // 2. 联表查询购物车+图书信息
        List<CartVO> cartList = cartMapper.selectCartList(userId);
        return cartList;
    }
}
