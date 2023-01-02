package com.example.expressdeliverysystemserver.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

// 解析请求头
public class UserRequest {
    // 获取当前请求头Token
    public static String getCurrentToken() {
        // request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取Authorization请求头
        String token = request.getHeader("Authorization");
        // 返回Token
        return token;
    }
}
