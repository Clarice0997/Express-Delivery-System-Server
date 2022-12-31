package com.example.expressdeliverysystemserver.service;

import com.example.expressdeliverysystemserver.entity.Account;
import com.example.expressdeliverysystemserver.entity.Bridge;
import com.example.expressdeliverysystemserver.entity.Login;
import com.example.expressdeliverysystemserver.mapper.LoginMapper;
import com.example.expressdeliverysystemserver.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    // 注入Mapper
    @Autowired
    private LoginMapper loginMapper;
    // 注入bcrypt
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * login Service
     *
     * @param account
     * @return
     */
    public Login login(Account account) {
        // 构建登录对象
        Login login = new Login();
        // 判断账号是否为空
        if (null == account.getUsername() || "".equals(account.getUsername())) {
            login.setCode(400);
            login.setMessage("账号不能为空");
            return login;
        }

        // 判断密码是否为空
        if (null == account.getPassword() || "".equals(account.getPassword())) {
            login.setCode(400);
            login.setMessage("密码不能为空");
            return login;
        }

        // 预处理 去除空字符
        account.setUsername(account.getUsername().trim());
        account.setPassword(account.getPassword().trim());
        // 获取当前登录用户账号
        String username = account.getUsername();
        String password = account.getPassword();

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
        if (loginMapper.getUser(username) == null) {
            System.out.println("用户不存在");
            login.setCode(404);
            login.setMessage("账号不存在");
            return login;
        } else {
            System.out.println("用户存在");
            // 查询账号id
            int id = loginMapper.getUid(username);
            // 持久化uid
            account.setUid(id);
        }

        // 判断账号密码是否相同
        // 获取原始密码
        String originPassword = loginMapper.getPassword(username);
        // bcrypt判断密码是否相同
        if (bCryptPasswordEncoder.matches(password, originPassword)) {
            String token = JWTUtils.generateToken(account);
            login.setCode(200);
            login.setToken(token);
            return login;
        } else {
            login.setCode(403);
            login.setMessage("账号密码错误");
            return login;
        }
    }

    /**
     * register Service
     *
     * @param account
     * @return
     */
    public Bridge register(Account account) {
        Bridge bridge = new Bridge();
        // 判断账号是否为空
        if (null == account.getUsername() || "".equals(account.getUsername())) {
            bridge.setCode(400);
            bridge.setMessage("账号不能为空");
            return bridge;
        }

        // 判断密码是否为空
        if (null == account.getPassword() || "".equals(account.getPassword())) {
            bridge.setCode(400);
            bridge.setMessage("密码不能为空");
            return bridge;
        }

        // 预处理 去除空字符
        account.setUsername(account.getUsername().trim());
        account.setPassword(account.getPassword().trim());
        // 获取当前注册用户账号
        String username = account.getUsername();
        String password = account.getPassword();

        // 判断账号长度是否合法
        if (username.length() < 6 || username.length() > 20) {
            bridge.setCode(400);
            bridge.setMessage("账号长度应为6-20位");
            return bridge;
        }

        // 判断密码长度是否合法
        if (password.length() < 6 || password.length() > 20) {
            bridge.setCode(400);
            bridge.setMessage("密码长度应为6-20位");
            return bridge;
        }

        // 判断账号是否存在
        if (loginMapper.getUser(username) == null) {
            System.out.println("用户不存在");
        } else {
            System.out.println("用户已存在");
            bridge.setCode(409);
            bridge.setMessage("账号已存在");
            return bridge;
        }

        // 加密密码
        password = bCryptPasswordEncoder.encode(password);
        // 重新赋值加密密码
        account.setPassword(password);

        // 判断是否注册成功
        if (loginMapper.register(account) != 0) {
            bridge.setCode(200);
            bridge.setMessage("注册成功");
        } else {
            bridge.setCode(400);
            bridge.setMessage("注册失败");
        }
        return bridge;
    }
}
