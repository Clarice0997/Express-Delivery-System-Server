package com.example.expressdeliverysystemserver.controller;

import com.example.expressdeliverysystemserver.entity.Admin;
import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.service.AdminManageService;
import com.example.expressdeliverysystemserver.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/admin/adminManage")
public class AdminManageController {
    // 注入Service
    @Autowired
    private AdminManageService adminManageService;

    // 查询快递员
    @ApiOperation(value = "查询快递员")
    @GetMapping("/admin")
    public Result query(@RequestParam(value = "pageNumber", required = false) Integer pageNumber, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Bridge bridge = adminManageService.query(pageNumber, pageSize);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("data", bridge.getCouriers()).data("count", bridge.getCount());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    // 根据ID查询快递员
    @ApiOperation(value = "根据ID查询快递员")

    @GetMapping("/admin/{id}")
    public Result queryById(@PathVariable Integer id) {
        Bridge bridge = adminManageService.queryById(id);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("data", bridge.getCourier());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    // 新增快递员
    @ApiOperation(value = "新增快递员")
    @PostMapping("/admin")
    public Result insertCourier(@RequestBody Admin admin) {
        System.out.println(admin);
        Bridge bridge = adminManageService.insertCourier(admin);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("message", bridge.getMessage());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }
}
