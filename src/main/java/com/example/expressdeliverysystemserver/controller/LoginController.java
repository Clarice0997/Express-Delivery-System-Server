package com.example.expressdeliverysystemserver.controller;

import com.example.expressdeliverysystemserver.service.LoginService;
import com.example.expressdeliverysystemserver.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/system/login")
public class LoginController {
    // 注入Service
    @Autowired
    private LoginService loginService;

    // 登录接口
    @ApiOperation(value = "登录用户")
    @PostMapping("/login")
    public Result login(){
        return Result.ok(200);
    }

    // 注册接口
    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public Result register(){
        return Result.ok(200);
    }
}
