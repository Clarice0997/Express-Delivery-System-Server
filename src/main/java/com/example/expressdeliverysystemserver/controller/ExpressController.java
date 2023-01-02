package com.example.expressdeliverysystemserver.controller;

import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Mail;
import com.example.expressdeliverysystemserver.service.ExpressService;
import com.example.expressdeliverysystemserver.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis/express")
@CrossOrigin
public class ExpressController {
    // 注入Service
    @Autowired
    private ExpressService expressService;

    @ApiOperation(value = "寄件")
    @PostMapping("/mail")
    public Result mail(Mail mail) {
        Bridge bridge = expressService.mail(mail);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("message", bridge.getMessage());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }
}
