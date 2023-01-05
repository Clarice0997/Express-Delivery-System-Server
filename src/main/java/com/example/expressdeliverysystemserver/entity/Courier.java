package com.example.expressdeliverysystemserver.entity;

import lombok.Data;

@Data
public class Courier {
    private Integer id;
    private String username;
    private String nickname;
    private String phone;
    private String status;
}
