package com.yinnnh.common;

import lombok.Getter;

@Getter
public enum ResultEnum {

    // 成功
    SUCCESS(200, "操作成功"),

    // 客户端错误 4xx
    PARAM_ERROR(400, "参数错误"),
    USER_NOT_EXIST(400, "账号不存在"),
    PASSWORD_ERROR(400, "密码错误"),
    UNAUTHORIZED(401, "未登录或登录失效,请先登录！"),
    FORBIDDEN(403, "无权限访问"),

    USER_EXIST(400, "账号已存在"),
    USER_NOT_LOGIN(400, "用户未登录"),
    NAME_OR_PASSWORD_NULL(400, "用户名或密码为空"),

    // 服务端错误 5xx
    SERVER_ERROR(500, "服务器异常");

    private final Integer code;
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}