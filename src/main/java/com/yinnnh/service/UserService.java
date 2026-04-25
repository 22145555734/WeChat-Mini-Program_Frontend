package com.yinnnh.service;

import com.yinnnh.common.Result;
import com.yinnnh.pojo.dto.UserLoginDTO;
import com.yinnnh.pojo.dto.UserRegisterDTO;
import com.yinnnh.pojo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Result login(UserLoginDTO loginDTO);

    Result register(UserRegisterDTO userRegisterDTO);

    Result<String> logout();


    Result<User> getUserInfo(Long userid);

    Result<String> updateProfile(User user);
}
