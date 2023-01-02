package com.example.expressdeliverysystemserver.service;

import com.example.expressdeliverysystemserver.entity.Account;
import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Mail;
import com.example.expressdeliverysystemserver.mapper.ExpressMapper;
import com.example.expressdeliverysystemserver.utils.JWTUtils;
import com.example.expressdeliverysystemserver.utils.TimeUtils;
import com.example.expressdeliverysystemserver.utils.UserRequest;
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
        // 保存订单号
        mail.setExpressID(expressID);

        // 获取用户对象 JWT
        String token = UserRequest.getCurrentToken();
        Account account = JWTUtils.verify(token);
        mail.setUid(account.getUid());

        // 插入寄件订单
        if (expressMapper.insertExpress(mail) != 0) {
            System.out.println("快件表插入成功");
        } else {
            bridge.setCode(400);
            bridge.setMessage("寄件失败");
        }

        // 插入快件寄件人表
        if (expressMapper.insertExpressSender(mail) != 0) {
            System.out.println("快件寄件人表插入成功");
        } else {
            bridge.setCode(400);
            bridge.setMessage("寄件失败");
        }

        // 插入快件收件人表
        if (expressMapper.insertExpressReceiver(mail) != 0) {
            System.out.println("快件收件人表插入成功");
        } else {
            bridge.setCode(400);
            bridge.setMessage("寄件失败");
        }

        // 插入快件时间表
        if (expressMapper.insertExpressDate(mail) != 0) {
            System.out.println("快件时间表插入成功");
        } else {
            bridge.setCode(400);
            bridge.setMessage("寄件失败");
        }

        // 返回成功寄件信息对象
        bridge.setCode(200);
        bridge.setMessage(mail.getExpressID());
        return bridge;
    }
}
