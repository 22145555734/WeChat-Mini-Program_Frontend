package com.yinnnh.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinnnh.common.Result;
import com.yinnnh.common.ResultEnum;
import com.yinnnh.utils.JwtUtil;
import com.yinnnh.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    // 用于把Result对象转成JSON字符串
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 放行跨域预检OPTIONS请求（必加，否则前端跨域会出问题）
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 2. 判断当前接口是否是公开接口（不需要登录）
        String requestURI = request.getRequestURI();
        if (isPublicUri(requestURI)) {
            return true; // 公开接口直接放行
        }

        // 3. 从请求头获取Token，兼容前端带Bearer前缀的写法
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 4. Token为空，返回未登录提示
        if (token == null || token.isBlank()) {
            writeUnauthorizedResponse(response);
            return false;
        }

        try {
            // 5. 解析Token，拿到用户ID
            Long userId = jwtUtil.getUserId(token);
            // 6. 把用户ID存入上下文，后续业务直接取
            UserContext.setUserId(userId);
            return true; // 校验通过，放行
        } catch (Exception e) {
            // 7. Token过期/伪造/解析失败，返回未登录提示
            writeUnauthorizedResponse(response);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束必须清理ThreadLocal，防止内存泄漏
        UserContext.clear();
    }

    /**
     * 配置公开接口列表：所有不需要登录就能访问的接口都加在这里
     */
    private boolean isPublicUri(String uri) {
        return uri.startsWith("/api/auth/login")    // 登录接口
                || uri.startsWith("/api/categories") // 分类列表（你当前报错的接口）
                || uri.startsWith("/api/book/hot")   // 热门书
                || uri.startsWith("/api/book/new")   // 新书
                || uri.startsWith("/api/book/")      // 图书详情、分类分页、搜索
                || uri.startsWith("/api/auth/register")
                ;
    }

    /**
     * 给前端写入标准的未登录JSON响应
     */
    private void writeUnauthorizedResponse(HttpServletResponse response) throws Exception {
        // 设置响应格式为JSON，编码UTF-8
        response.setContentType("application/json;charset=UTF-8");
        // 设置HTTP状态码401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 写入Result格式的JSON数据
        PrintWriter writer = response.getWriter();
        Result<Void> result = Result.error(ResultEnum.UNAUTHORIZED);
        writer.write(OBJECT_MAPPER.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}