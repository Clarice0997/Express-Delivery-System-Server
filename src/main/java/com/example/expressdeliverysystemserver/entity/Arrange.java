package com.example.expressdeliverysystemserver.entity;

import lombok.Data;

@Data
public class Arrange {
    private String expressId;
    private Integer courierId;
    private Integer deliveryId;
    private String status;
}
