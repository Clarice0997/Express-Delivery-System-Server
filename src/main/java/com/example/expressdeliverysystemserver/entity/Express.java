package com.example.expressdeliverysystemserver.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Express {
    public String expressId;
    public String senderName;
    public String senderPhone;
    public String senderProvince;
    public String senderCity;
    public String senderDistrict;
    public String senderAddress;
    public String receiverName;
    public String receiverPhone;
    public String receiverProvince;
    public String receiverCity;
    public String receiverDistrict;
    public String receiverAddress;
    public String status;
    public String weight;
    public String type;
    public Date orderTime;
}
