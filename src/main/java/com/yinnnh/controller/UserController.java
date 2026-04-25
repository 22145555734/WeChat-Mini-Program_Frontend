package com.yinnnh.controller;

import com.yinnnh.common.Result;
import com.yinnnh.pojo.dto.UserLoginDTO;
import com.yinnnh.pojo.dto.UserRegisterDTO;
import com.yinnnh.pojo.entity.User;
import com.yinnnh.service.UserService;
import com.yinnnh.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;
    private JwtUtil jwtUtil;

    //注册接口
    @PostMapping("/api/auth/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO){
        return userService.register(userRegisterDTO);
    }

    // 登录接口
    @PostMapping("/api/auth/login")
    public Result login(@RequestBody UserLoginDTO loginDTO) {
        return userService.login(loginDTO);
    }
    //退出登录
    @PostMapping("/api/auth/logout")
    public Result<String> logout(HttpServletRequest request) {
        // 清除Session
        request.getSession().invalidate();
        return userService.logout();
    }
    // 显示用户信息
    @GetMapping("/api/auth/profile")
    public Result<User> getUserInfo(Long userid){
        return userService.getUserInfo(userid);
    }

    // 更新用户信息
    @PutMapping("/api/user/profile")
    public Result<String> updateProfile(@RequestBody User user) {
        return userService.updateProfile(user);
    }
}
