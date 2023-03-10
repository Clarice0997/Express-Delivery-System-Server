package com.example.expressdeliverysystemserver.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private Integer type;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
