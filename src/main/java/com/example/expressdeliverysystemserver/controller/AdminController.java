package com.example.expressdeliverysystemserver.controller;

import com.example.expressdeliverysystemserver.entity.Admin;
import com.example.expressdeliverysystemserver.entity.Login;
import com.example.expressdeliverysystemserver.service.AdminService;
import com.example.expressdeliverysystemserver.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/system/admin")
public class AdminController {
    // 注入Service
    @Autowired
    private AdminService adminService;

    // 登录接口
    @ApiOperation(value = "登录后台")
    @PostMapping("/login")
    public Result login(Admin admin) {
        Login login = adminService.login(admin);
        if (login.getCode() == 200) {
            return Result.ok(login.getCode()).data("token", login.getToken());
        } else {
            return Result.error(login.getCode()).data("message", login.getMessage());
        }
    }
}
