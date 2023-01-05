package com.example.expressdeliverysystemserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan("com.example.expressdeliverysystemserver.mapper")
public class ExpressDeliverySystemServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpressDeliverySystemServerApplication.class, args);
    }

    // 注入bcrypt
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
