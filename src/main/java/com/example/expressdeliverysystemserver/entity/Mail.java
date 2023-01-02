package com.example.expressdeliverysystemserver.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Mail {
    @NotNull(message = "信息不能为空")
    private String sender_name;
    @NotNull(message = "信息不能为空")
    private String sender_phone;
    @NotNull(message = "信息不能为空")
    private String sender_province;
    @NotNull(message = "信息不能为空")
    private String sender_city;
    @NotNull(message = "信息不能为空")
    private String sender_district;
    @NotNull(message = "信息不能为空")
    private String sender_address;
    @NotNull(message = "信息不能为空")
    private String receiver_name;
    @NotNull(message = "信息不能为空")
    private String receiver_phone;
    @NotNull(message = "信息不能为空")
    private String receiver_province;
    @NotNull(message = "信息不能为空")
    private String receiver_city;
    @NotNull(message = "信息不能为空")
    private String receiver_district;
    @NotNull(message = "信息不能为空")
    private String receiver_address;
    @NotNull(message = "信息不能为空")
    private String type;
    @NotNull(message = "信息不能为空")
    private String weight;

    private String expressID;
    private int uid;
}
