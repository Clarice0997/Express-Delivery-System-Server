package com.example.expressdeliverysystemserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    // 注入bcrypt
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
}
