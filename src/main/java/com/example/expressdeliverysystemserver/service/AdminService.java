package com.example.expressdeliverysystemserver.service;

import com.example.expressdeliverysystemserver.entity.Admin;
import com.example.expressdeliverysystemserver.entity.Login;
import com.example.expressdeliverysystemserver.mapper.AdminMapper;
import com.example.expressdeliverysystemserver.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    // 注入Mapper
    @Autowired
    private AdminMapper adminMapper;
    // 注入bcrypt
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Login login(Admin admin) {
        // 构建登录对象
        Login login = new Login();
        // 判断账号是否为空
        if (null == admin.getUsername() || "".equals(admin.getUsername())) {
            login.setCode(400);
            login.setMessage("账号不能为空");
            return login;
        }

        // 判断密码是否为空
        if (null == admin.getPassword() || "".equals(admin.getPassword())) {
            login.setCode(400);
            login.setMessage("密码不能为空");
            return login;
        }

        // 预处理 去除空字符
        admin.setUsername(admin.getUsername().trim());
        admin.setPassword(admin.getPassword().trim());
        // 获取当前登录用户账号
        String username = admin.getUsername();
        String password = admin.getPassword();

        // 判断账号长度是否合法
        if (username.length() < 6 || username.length() > 20) {
            login.setCode(400);
            login.setMessage("账号长度应为6-20位");
            return login;
        }

        // 判断密码长度是否合法
        if (password.length() < 6 || password.length() > 20) {
            login.setCode(400);
            login.setMessage("密码长度应为6-20位");
            return login;
        }

        // 判断账号是否存在
        if (adminMapper.getUser(username) == null) {
            System.out.println("用户不存在");
            login.setCode(404);
            login.setMessage("账号不存在");
            return login;
        } else {
            System.out.println("用户存在");
        }

        // 查询账号id
        int id = adminMapper.getUid(username);
        // 持久化uid
        admin.setId(id);

        // 判断当前用户状态
        // 账号状态 0下班状态 1上班状态
        if (adminMapper.getStatus(admin.getId()) == 0) {
            login.setCode(400);
            login.setMessage("当前用户不可用");
            return login;
        }

        // 判断账号密码是否相同
        // 获取原始密码
        String originPassword = adminMapper.getPassword(username);

        // 未加密密码比对
        if (password.equals(originPassword)) {
            // 查询账号类型
            int type = adminMapper.getType(id);
            // 持久化账号类型
            admin.setType(type);
            // 查询账号状态
            int status = adminMapper.getStatus(id);
            // 持久化账号状态
            admin.setStatus(status);

            // 获取Token
            String token = JWTUtils.generateAdminToken(admin);
            login.setCode(200);
            login.setToken(token);
            return login;
        } else {
            login.setCode(403);
            login.setMessage("账号密码错误");
            return login;
        }
    }
}
