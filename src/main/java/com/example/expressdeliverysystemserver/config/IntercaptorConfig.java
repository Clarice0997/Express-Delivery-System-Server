package com.example.expressdeliverysystemserver.config;

import com.example.expressdeliverysystemserver.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercaptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                // 拦截的路径
                .addPathPatterns("/apis/express/mail")
                // 排除登录接口
                .excludePathPatterns("/system/user/**","/system/admin/login");
    }
}