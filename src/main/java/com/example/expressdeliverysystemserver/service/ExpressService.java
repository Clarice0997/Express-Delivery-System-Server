package com.example.expressdeliverysystemserver.service;

import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Mail;
import com.example.expressdeliverysystemserver.mapper.ExpressMapper;
import com.example.expressdeliverysystemserver.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExpressService {
    // 注入Mapper
    @Autowired
    private ExpressMapper expressMapper;

    /**
     * mail Service
     *
     * @param mail
     * @return
     */
    public Bridge mail(Mail mail) {
        Bridge bridge = new Bridge();
        // 创建订单号
        // 生成时间戳
        String timestamp = String.valueOf(TimeUtils.getTimeHandle());
        // 生成两位随机数
        Random random = new Random();
        int randomNum = random.nextInt(100);
        String randomString = randomNum + "";
        // 补零
        if (randomNum < 10) {
            randomString = "0" + randomNum;
        }
        // 拼接订单号
        String expressID = timestamp + randomString;
        System.out.println(expressID);

        bridge.setCode(200);
        bridge.setMessage(mail.toString());
        return bridge;
    }
}