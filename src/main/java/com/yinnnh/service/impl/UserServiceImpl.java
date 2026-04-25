package com.yinnnh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yinnnh.common.Result;
import com.yinnnh.common.ResultEnum;
import com.yinnnh.exception.BizException;
import com.yinnnh.mapper.UserMapper;
import com.yinnnh.pojo.dto.UserLoginDTO;
import com.yinnnh.pojo.dto.UserRegisterDTO;
import com.yinnnh.pojo.entity.User;
import com.yinnnh.pojo.vo.UserLoginVO;
import com.yinnnh.service.UserService;
import com.yinnnh.utils.JwtUtil;
import com.yinnnh.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@SuppressWarnings("unchecked")//???强制无视警告
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private RedisUtil redisUtil;
    private HttpServletRequest request;
    private JwtUtil jwtUtil;

    //用户登录
    @Override
    public Result login(UserLoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        // 1. 非空校验，直接抛异常
        if (username.isBlank() || password.isBlank()) {
            throw new BizException(ResultEnum.NAME_OR_PASSWORD_NULL);
        }

        // 2. 查用户
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, username)
                        .eq(User::getDeleted, 0)
        );

        // 3. 用户不存在，直接抛
        if (user == null) {
            throw new BizException(ResultEnum.USER_NOT_EXIST);
        }

        // 4. 密码错误，直接抛
        if (!password.equals(user.getPassword())) {
            throw new BizException(ResultEnum.PASSWORD_ERROR);
        }

        // 5. 生成 JWT
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        String token = jwtUtil.generateToken(map);

        // 6. 封装返回
        UserLoginVO vo = new UserLoginVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setToken(token);

        return Result.success(vo);
    }


    //用户注册
    @Override
    public Result<String> register(UserRegisterDTO dto) {
        // 1. 校验用户名是否重复
        User existUser = userMapper.selectByUsername(dto.getUsername());
        if (existUser != null) {
            return Result.error(400, "用户名已被注册");
        }
        // 2. 校验邮箱是否重复
        User existEmail = userMapper.selectByEmail(dto.getEmail());
        if (existEmail != null) {
            return Result.error(400, "邮箱已被注册");
        }
        // 3. 校验手机号是否重复
        User existPhone = userMapper.selectByPhone(dto.getPhone());
        if (existPhone != null) {
            return Result.error(400, "手机号已被注册");
        }
        // 4. 封装用户信息
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(dto.getPassword());
        newUser.setPhone(dto.getPhone());
        newUser.setEmail(dto.getEmail());
        newUser.setNickname(dto.getNickname());
        newUser.setAvatar(dto.getAvatar());
        // 5. 插入数据库
        userMapper.insert(newUser);
        return Result.success("恭喜您注册成功！");
    }

    //用户登出
    @Override
    public Result<String> logout() {
        //redisUtil.deleteToken(token);
        return Result.success("退出成功");
    }


    //展示用户信息
    @Override
    public Result<User> getUserInfo(Long userid) {
        // 1. 直接根据id查数据库（MyBatis-Plus 自带方法）//mp的ob强转user
        User user =  userMapper.selectById(userid);

        // 2. 安全处理：不返回密码
        if (user != null) {
            user.setPassword(null);
        }

        // 3. 返回用户数据
        return Result.success(user);
    }


    //更新用户信息
    @Override
    public Result<String> updateProfile(User user) {
        // 1. 查用户是否存在
        User existUser = userMapper.selectById(user.getId());
        if (existUser == null) {
            return Result.error(400, "更新失败，用户不存在");
        }

        // 2. 把旧密码填回去（保证数据库不会报错）
        user.setUsername(existUser.getUsername());
        user.setPassword(existUser.getPassword());
        user.setEmail(existUser.getEmail());
        user.setPhone(existUser.getPhone());
        // 3. 这一句就够了，直接更新
        userMapper.updateById(user);

        return Result.success("更新成功");
    }
}
