package com.yinnnh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yinnnh.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //根据用户户名查询用户
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    // 根据邮箱查询
    @Select("select * from user where email = #{email}")
    User selectByEmail(String email);

    // 根据手机号查询
    @Select("select * from user where phone = #{phone}")
    User selectByPhone(String phone);
}
