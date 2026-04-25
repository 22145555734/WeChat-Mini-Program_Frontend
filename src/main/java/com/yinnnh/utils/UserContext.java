package com.yinnnh.utils;

public class UserContext {
    private static final ThreadLocal<Long> USER_ID_THREAD_LOCAL = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        USER_ID_THREAD_LOCAL.set(userId);
    }

    public static Long getUserId() {
        return USER_ID_THREAD_LOCAL.get();
    }

    public static void clear() {
        USER_ID_THREAD_LOCAL.remove();
    }
}