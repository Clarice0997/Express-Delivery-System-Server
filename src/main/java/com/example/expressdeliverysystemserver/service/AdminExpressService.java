package com.example.expressdeliverysystemserver.service;

import com.example.expressdeliverysystemserver.entity.Admin;
import com.example.expressdeliverysystemserver.entity.Arrange;
import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Express;
import com.example.expressdeliverysystemserver.mapper.AdminExpressMapper;
import com.example.expressdeliverysystemserver.utils.JWTUtils;
import com.example.expressdeliverysystemserver.utils.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminExpressService {
    // 注入Mapper
    @Autowired
    private AdminExpressMapper adminExpressMapper;

    /**
     * getAllExpresses Service
     *
     * @param status
     * @return
     */
    public Bridge getAllExpresses(String status) {
        Bridge bridge = new Bridge();
        List<Express> expresses;

        // 判断状态是否存在
        if (status == null) {
            expresses = adminExpressMapper.getAllExpresses();
        } else {
            expresses = adminExpressMapper.getAllExpressesWithStatus(status);
        }

        // 判断数据是否存在
        if (expresses.isEmpty()) {
            bridge.setCode(400);
            bridge.setMessage("数据不存在");
            return bridge;
        }

        // 返回对象
        bridge.setCode(200);
        bridge.setExpresses(expresses);
        return bridge;
    }

    /**
     * arrangeCourier Service
     *
     * @param arrange
     * @return
     */
    public Bridge arrangeCourier(Arrange arrange) {
        Bridge bridge = new Bridge();
        int i = 0;

        // 判断接口类型
        if (arrange.getCourierId() != null) {
            // 修改快件状态 发配揽收快递员
            i = adminExpressMapper.arrangeCourier(arrange);
        } else if (arrange.getDeliveryId() != null) {
            // 修改快件状态 发配派件快递员
            i = adminExpressMapper.arrangeDelivery(arrange);
        } else {
            bridge.setCode(400);
            bridge.setMessage("请求不合法");
            return bridge;
        }

        // 判断请求是否成功
        if (i == 0) {
            bridge.setCode(400);
            bridge.setMessage("请求失败");
            return bridge;
        }

        // 返回对象
        bridge.setCode(200);
        bridge.setMessage("请求成功");
        return bridge;
    }

    /**
     * rejectExpress Service
     *
     * @param expressId
     * @return
     */
    public Bridge rejectExpress(String expressId) {
        Bridge bridge = new Bridge();

        int i = adminExpressMapper.rejectExpress(expressId);

        // 判断请求是否成功
        if (i == 0) {
            bridge.setCode(400);
            bridge.setMessage("请求失败");
            return bridge;
        }

        // 返回对象
        bridge.setCode(200);
        bridge.setMessage("请求成功");
        return bridge;
    }

    /**
     * getSelfExpresses Service
     *
     * @param status
     * @return
     */
    public Bridge getSelfExpresses(String status) {
        Bridge bridge = new Bridge();
        List<Express> expresses;

        // 获取用户对象 JWT
        String token = UserRequest.getCurrentToken();
        Admin admin = JWTUtils.verifyAdmin(token);

        // 判断状态是否存在
        if (status == null) {
            expresses = adminExpressMapper.getAllSelfExpresses(admin.getId());
        } else {
            expresses = adminExpressMapper.getAllSelfExpressesWithStatus(admin.getId(), status);
        }

        // 判断数据是否存在
        if (expresses.isEmpty()) {
            bridge.setCode(201);
            bridge.setMessage("数据不存在");
            return bridge;
        }

        // 返回对象
        bridge.setCode(200);
        bridge.setExpresses(expresses);
        return bridge;
    }

    /**
     * courierHandle Service
     *
     * @param expressId
     * @param status
     * @return
     */
    public Bridge courierHandle(String expressId, String status) {
        Bridge bridge = new Bridge();

        int i = adminExpressMapper.updateExpressStatus(expressId, status);

        // 判断请求是否成功
        if (i == 0) {
            bridge.setCode(400);
            bridge.setMessage("请求失败");
            return bridge;
        }

        // 返回对象
        bridge.setCode(200);
        bridge.setMessage("请求成功");
        return bridge;
    }
}
