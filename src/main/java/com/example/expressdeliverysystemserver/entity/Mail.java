package com.example.expressdeliverysystemserver.entity;

import lombok.Data;

@Data
public class Mail {
    private String sender_name;
    private String sender_phone;
    private String sender_province;
    private String sender_city;
    private String sender_district;
    private String sender_address;
    private String receiver_name;
    private String receiver_phone;
    private String receiver_province;
    private String receiver_city;
    private String receiver_district;
    private String receiver_address;
    private String type;
    private String weight;
}
