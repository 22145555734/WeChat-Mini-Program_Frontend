package com.yinnnh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yinnnh.pojo.entity.Cart;
import com.yinnnh.pojo.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    //根据用户user_id查询购物车
    @Select("SELECT c.id, c.user_id, c.book_id, c.quantity, c.selected, " +
            "b.title, b.author, b.cover, b.price, b.stock " +
            "FROM cart c " +
            "LEFT JOIN book b ON c.book_id = b.id " +
            "WHERE c.user_id = #{userId} AND c.deleted = 0 " +
            "ORDER BY c.create_time DESC")
    List<CartVO> selectCartList(@Param("userId") Long userId);
}
