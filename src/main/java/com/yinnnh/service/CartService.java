package com.yinnnh.service;

import com.yinnnh.pojo.vo.CartVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<CartVO> getCartList(Long userId);
}
