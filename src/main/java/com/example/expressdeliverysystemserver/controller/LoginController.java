package com.example.expressdeliverysystemserver.controller;

import com.example.expressdeliverysystemserver.entity.Account;
import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Login;
import com.example.expressdeliverysystemserver.entity.UserInfo;
import com.example.expressdeliverysystemserver.service.LoginService;
import com.example.expressdeliverysystemserver.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/system/user")
public class LoginController {
    // 注入Service
    @Autowired
    private LoginService loginService;

    // 登录接口
    @ApiOperation(value = "登录用户")
    @PostMapping("/login")
    public Result login(Account account) {
        Login login = loginService.login(account);
        if (login.getCode() == 200) {
            return Result.ok(login.getCode()).data("token", login.getToken());
        } else {
            return Result.error(login.getCode()).data("message", login.getMessage());
        }
    }

    // 注册接口
    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public Result register(Account account) {
        Bridge bridge = loginService.register(account);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("message", bridge.getMessage());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    // 获取用户信息接口
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/userInfo")
    public Result getInfo() {
        UserInfo userInfo = loginService.getInfo();
        return Result.ok(200).data("data", userInfo);
    }
}
