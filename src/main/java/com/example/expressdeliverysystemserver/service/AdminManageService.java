package com.example.expressdeliverysystemserver.service;

import com.example.expressdeliverysystemserver.entity.Admin;
import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Courier;
import com.example.expressdeliverysystemserver.mapper.AdminManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class AdminManageService {
    // 注入Mapper
    @Autowired
    private AdminManageMapper adminManageMapper;

    /**
     * query Service
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Bridge query(Integer pageNumber, Integer pageSize) {
        Bridge bridge = new Bridge();
        // 判断pageNumber是否存在
        if (null == pageNumber) {
            pageNumber = 1;
        }
        // 判断pageNumber是否存在
        if (null == pageSize) {
            pageSize = 5;
        }

        // 查询快递员信息
        List<Courier> couriers = adminManageMapper.selectCourier((pageNumber - 1) * pageSize, pageSize);

        // 查询快递员数据条数
        Integer count = adminManageMapper.selectCountCourier();

        // 判断查询数据是否为空
        if (couriers.isEmpty() || count == null) {
            bridge.setCode(400);
            bridge.setMessage("数据不存在");
            return bridge;
        }

        // 返回查询快递员对象
        bridge.setCode(200);
        bridge.setCouriers(couriers);
        bridge.setCount(count);
        return bridge;
    }

    /**
     * queryById Service
     *
     * @param id
     * @return
     */
    public Bridge queryById(Integer id) {
        Bridge bridge = new Bridge();
        // 判断id是否存在
        if (id == null) {
            bridge.setCode(400);
            bridge.setMessage("用户id不能为空");
            return bridge;
        }

        // 判断查询数据是否为空
        if (adminManageMapper.selectCourierById(id) == null) {
            bridge.setCode(400);
            bridge.setMessage("不存在快递员");
            return bridge;
        }

        // 根据id查询对象
        Courier courier = adminManageMapper.selectCourierById(id);

        // 返回查询快递员对象
        bridge.setCode(200);
        bridge.setCourier(courier);
        return bridge;
    }

    /**
     * insertCourier Service
     *
     * @param admin
     * @return
     */
    public Bridge insertCourier(Admin admin) {
        Bridge bridge = new Bridge();

        // 判断信息完整性
        if (admin.getUsername() == null && admin.getPassword() == null) {
            bridge.setCode(400);
            bridge.setMessage("信息不完整");
            return bridge;
        }


        // 判断用户是否已存在
        if (adminManageMapper.isExistUsername(admin.getUsername()) != null) {
            bridge.setCode(400);
            bridge.setMessage("用户已存在");
            return bridge;
        }

        // 判断电话号码是否已存在
        if (adminManageMapper.isExistPhone(admin.getPhone()) != null) {
            bridge.setCode(400);
            bridge.setMessage("电话号码已被使用");
            return bridge;
        }

        // 插入数据库
        int i = adminManageMapper.insertCourier(admin);

        // 判断是否插入成功
        if (i == 1) {
            // 返回成功对象
            bridge.setCode(200);
            bridge.setMessage("插入快递员成功");
            return bridge;
        } else {
            // 返回失败对象
            bridge.setCode(400);
            bridge.setMessage("插入快递员失败");
            return bridge;
        }

    }
}
