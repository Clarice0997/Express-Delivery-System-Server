package com.example.expressdeliverysystemserver.entity;

import lombok.Data;

@Data
public class Admin {
    private int id;
    private String username;
    private String password;
    private int type;
    private int status;
}
