//package com.yinnnh.config;
//
//import com.yinnnh.interceptor.LoginCheckInterceptor;
//import jakarta.annotation.Resource;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Resource
//    private LoginCheckInterceptor loginCheckInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginCheckInterceptor)
//                .addPathPatterns("/api/**")          // 拦截所有接口
//                .excludePathPatterns(                // 放行不需要登录的
//                        "/api/auth/login",
//                        "/api/auth/register"
//                );
//    }
//}