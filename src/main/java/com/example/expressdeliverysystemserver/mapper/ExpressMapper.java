package com.example.expressdeliverysystemserver.mapper;

import com.example.expressdeliverysystemserver.entity.Mail;
import com.example.expressdeliverysystemserver.entity.Query;
import org.apache.ibatis.annotations.*;

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

    // 查询快递单号是否存在
    @Select("select * from express where express_id = #{expressID}")
    public Mail isExpress(String expressID);

    // 查询快递信息（多表）
    @Select("select es.sender_name       'sender_name',\n" +
            "       es.sender_phone      'sender_phone',\n" +
            "       es.sender_province   'sender_province',\n" +
            "       es.sender_city       \"sender_city\",\n" +
            "       es.sender_district   \"sender_district\",\n" +
            "       es.sender_address    \"sender_address\",\n" +
            "       er.receiver_name     \"receiver_name\",\n" +
            "       er.receiver_phone    \"receiver_phone\",\n" +
            "       er.receiver_province \"receiver_province\",\n" +
            "       er.receiver_city     \"receiver_city\",\n" +
            "       er.receiver_district \"receiver_district\",\n" +
            "       er.receiver_address  \"receiver_address\",\n" +
            "       e.type               \"type\",\n" +
            "       e.weight             \"weight\"\n" +
            "from express e\n" +
            "         left outer join express_sender es on e.express_id = es.express_id\n" +
            "         left outer join express_receiver er on e.express_id = er.express_id\n" +
            "where e.express_id = #{expressID}")
    public Query selectExpress(String expressID);
}
