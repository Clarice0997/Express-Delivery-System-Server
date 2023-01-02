package com.example.expressdeliverysystemserver.controller;

import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Mail;
import com.example.expressdeliverysystemserver.service.ExpressService;
import com.example.expressdeliverysystemserver.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/express")
@CrossOrigin
public class ExpressController {
    // 注入Service
    @Autowired
    private ExpressService expressService;

    // 寄件
    @ApiOperation(value = "寄件")
    @PostMapping("/mail")
    public Result mail(Mail mail) {
        Bridge bridge = expressService.mail(mail);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("expressID", bridge.getMessage());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    // 快件查询
    @ApiOperation(value = "快件查询")
    @GetMapping ("/query/{expressID}")
    public Result query(@PathVariable String expressID){
        Bridge bridge = expressService.query(expressID);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("data", bridge.getQuery());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }
}
