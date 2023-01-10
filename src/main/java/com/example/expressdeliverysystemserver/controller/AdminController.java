package com.example.expressdeliverysystemserver.controller;

import com.example.expressdeliverysystemserver.entity.Admin;
import com.example.expressdeliverysystemserver.entity.Login;
import com.example.expressdeliverysystemserver.service.AdminService;
import com.example.expressdeliverysystemserver.utils.Result;
import com.example.expressdeliverysystemserver.utils.UserRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.expressdeliverysystemserver.utils.JWTUtils.verifyAdmin;

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

    // 获取用户信息接口
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getInfo")
    public Result getInfo() {
        // 获取token
        String token = UserRequest.getCurrentToken();
        // 判断token是否存在
        if (token == null) {
            return Result.error(401).data("message", "身份认证不通过");
        }
        // 解码JWT
        Admin admin = verifyAdmin(token);
        if (admin != null) {
            return Result.ok(200).data("data", admin);
        } else {
            return Result.error(401).data("message", "身份认证不通过");
        }
    }
}
