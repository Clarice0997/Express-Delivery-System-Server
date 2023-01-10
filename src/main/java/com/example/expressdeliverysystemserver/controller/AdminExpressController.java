package com.example.expressdeliverysystemserver.controller;

import com.example.expressdeliverysystemserver.entity.Arrange;
import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Express;
import com.example.expressdeliverysystemserver.service.AdminExpressService;
import com.example.expressdeliverysystemserver.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/admin/express")
public class AdminExpressController {
    // 注入Service
    @Autowired
    private AdminExpressService adminExpressService;

    // 管理员获取所有快递
    @ApiOperation(value = "管理员获取所有快递")
    @GetMapping("/express")
    public Result getAllExpresses(@RequestParam(value = "status", required = false) String status) {
        Bridge bridge = adminExpressService.getAllExpresses(status);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("data", bridge.getExpresses());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    // 揽收派送接口
    @ApiOperation(value = "揽收派送")
    @PutMapping("/express/arrange")
    public Result arrangeCourier(@RequestBody Arrange arrange) {
        Bridge bridge = adminExpressService.arrangeCourier(arrange);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("message", bridge.getMessage());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    // 拒收快递接口
    @ApiOperation(value = "拒收快递")
    @PutMapping("/express/rejection/{expressId}")
    public Result rejectExpress(@PathVariable String expressId) {
        Bridge bridge = adminExpressService.rejectExpress(expressId);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("message", bridge.getMessage());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    // 快递员获取所有快递
    @ApiOperation(value = "快递员获取所有快递")
    @GetMapping("/courier/express")
    public Result getSelfExpresses(@RequestParam(value = "status", required = false) String status) {
        Bridge bridge = adminExpressService.getSelfExpresses(status);
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("data", bridge.getExpresses());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    // 快递员揽收拒收快递接口
    @ApiOperation(value = "快递员揽收拒收快递")
    @PutMapping("/courier/express/{expressId}")
    public Result courierHandle(@PathVariable String expressId, @RequestBody Express express) {
        Bridge bridge = adminExpressService.courierHandle(expressId, express.getStatus());
        if (bridge.getCode() == 200) {
            return Result.ok(bridge.getCode()).data("message", bridge.getMessage());
        } else {
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }
}
