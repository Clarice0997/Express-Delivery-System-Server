package com.example.expressdeliverysystemserver.config;

import com.example.expressdeliverysystemserver.interceptor.AdminJWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminIntercaptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminJWTInterceptor())
                // 拦截的路径
                .addPathPatterns("/apis/admin/**")
                // 排除登录接口
                .excludePathPatterns("/system/admin/login");
    }
}
