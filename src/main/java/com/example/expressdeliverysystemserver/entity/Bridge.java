package com.example.expressdeliverysystemserver.entity;

import lombok.Data;

import java.util.List;

@Data
public class Bridge {
    private int code;
    private String message;
    private Query query;
    private List<Courier> couriers;
    private Courier courier;
}
