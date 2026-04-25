package com.yinnnh.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    private final StringRedisTemplate redisTemplate;

    public RedisUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 存 token，过期时间单位：分钟
    public void setToken(String token, String userId, long expireMinutes) {
        long expireSeconds = expireMinutes * 60L;
        redisTemplate.opsForValue().set("login_token:" + token, userId, expireSeconds);
    }

    // 根据 token 获取用户ID
    public String getUserIdByToken(String token) {
        return redisTemplate.opsForValue().get("login_token:" + token);
    }

    // 删除 token（退出登录）
    public void deleteToken(String token) {
        redisTemplate.delete("login_token:" + token);
    }
}