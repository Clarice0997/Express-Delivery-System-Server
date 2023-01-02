package com.example.expressdeliverysystemserver.mapper;

import com.example.expressdeliverysystemserver.entity.Mail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExpressMapper {
    // 插入快件表
    @Insert("insert into express(express_id, uid, type, weight, status) values (#{expressID},#{uid},#{type},#{weight},0)")
    public int insertExpress(Mail mail);

    // 插入快件寄件人表
    @Insert("insert into express_sender values(null,#{expressID},#{sender_name},#{sender_phone},#{sender_province},#{sender_city},#{sender_district},#{sender_address})")
    public int insertExpressSender(Mail mail);

    // 插入快件收件人表
    @Insert("insert into express_receiver values(null,#{expressID},#{receiver_name},#{receiver_phone},#{receiver_province},#{receiver_city},#{receiver_district},#{receiver_address})")
    public int insertExpressReceiver(Mail mail);

    // 插入快件时间表
    @Insert("insert into express_date(id,express_id,order_time) value(null,#{expressID},now())")
    public int insertExpressDate(Mail mail);
}
