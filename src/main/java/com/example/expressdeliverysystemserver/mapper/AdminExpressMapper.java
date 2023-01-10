package com.example.expressdeliverysystemserver.mapper;

import com.example.expressdeliverysystemserver.entity.Arrange;
import com.example.expressdeliverysystemserver.entity.Express;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminExpressMapper {
    // 管理员获取所有快递
    @Select("select e.express_id         express_id,\n" +
            "       es.sender_name       sender_name,\n" +
            "       es.sender_phone      sender_phone,\n" +
            "       es.sender_province   sender_province,\n" +
            "       es.sender_city       sender_city,\n" +
            "       es.sender_district   sender_district,\n" +
            "       es.sender_address    sender_address,\n" +
            "       er.receiver_name     receiver_name,\n" +
            "       er.receiver_phone    receiver_phone,\n" +
            "       er.receiver_province receiver_province,\n" +
            "       er.receiver_city     receiver_city,\n" +
            "       er.receiver_district receiver_district,\n" +
            "       er.receiver_address  receiver_address,\n" +
            "       e.status             status,\n" +
            "       e.type               type,\n" +
            "       e.weight             weight,\n" +
            "       ed.order_time        order_time\n" +
            "from express e\n" +
            "         left outer join express_sender es on e.express_id = es.express_id\n" +
            "         left outer join express_receiver er on e.express_id = er.express_id\n" +
            "         left outer join express_date ed on e.express_id = ed.express_id")
    public List<Express> getAllExpresses();

    // 管理员获取所有快递（带状态）
    @Select("select e.express_id         express_id,\n" +
            "       es.sender_name       sender_name,\n" +
            "       es.sender_phone      sender_phone,\n" +
            "       es.sender_province   sender_province,\n" +
            "       es.sender_city       sender_city,\n" +
            "       es.sender_district   sender_district,\n" +
            "       es.sender_address    sender_address,\n" +
            "       er.receiver_name     receiver_name,\n" +
            "       er.receiver_phone    receiver_phone,\n" +
            "       er.receiver_province receiver_province,\n" +
            "       er.receiver_city     receiver_city,\n" +
            "       er.receiver_district receiver_district,\n" +
            "       er.receiver_address  receiver_address,\n" +
            "       e.status             status,\n" +
            "       e.type               type,\n" +
            "       e.weight             weight,\n" +
            "       ed.order_time        order_time\n" +
            "from express e\n" +
            "         left outer join express_sender es on e.express_id = es.express_id\n" +
            "         left outer join express_receiver er on e.express_id = er.express_id\n" +
            "         left outer join express_date ed on e.express_id = ed.express_id\n" +
            "where e.status = #{status}")
    public List<Express> getAllExpressesWithStatus(String status);

    // 修改快件状态 发配揽收快递员
    @Update("update express set courier_id = #{courierId},status = #{status} where express_id = #{expressId}")
    public int arrangeCourier(Arrange arrange);

    // 修改快件状态 发配派件快递员
    @Update("update express set delivery_id = #{deliveryId},status = #{status} where express_id = #{expressId}")
    public int arrangeDelivery(Arrange arrange);

    // 拒收快递
    @Update("update express set status = '9' where express_id = #{expressId}")
    public int rejectExpress(String expressId);

    // 快递员获取所有快递
    @Select("select e.express_id         express_id,\n" +
            "       es.sender_name       sender_name,\n" +
            "       es.sender_phone      sender_phone,\n" +
            "       es.sender_province   sender_province,\n" +
            "       es.sender_city       sender_city,\n" +
            "       es.sender_district   sender_district,\n" +
            "       es.sender_address    sender_address,\n" +
            "       er.receiver_name     receiver_name,\n" +
            "       er.receiver_phone    receiver_phone,\n" +
            "       er.receiver_province receiver_province,\n" +
            "       er.receiver_city     receiver_city,\n" +
            "       er.receiver_district receiver_district,\n" +
            "       er.receiver_address  receiver_address,\n" +
            "       e.status             status,\n" +
            "       e.type               type,\n" +
            "       e.weight             weight,\n" +
            "       ed.order_time        order_time\n" +
            "from express e\n" +
            "         left outer join express_sender es on e.express_id = es.express_id\n" +
            "         left outer join express_receiver er on e.express_id = er.express_id\n" +
            "         left outer join express_date ed on e.express_id = ed.express_id\n" +
            "where e.courier_id = #{uid} or e.delivery_id = #{uid}")
    public List<Express> getAllSelfExpresses(int uid);

    // 管理员获取所有快递（带状态）
    @Select("select e.express_id         express_id,\n" +
            "       es.sender_name       sender_name,\n" +
            "       es.sender_phone      sender_phone,\n" +
            "       es.sender_province   sender_province,\n" +
            "       es.sender_city       sender_city,\n" +
            "       es.sender_district   sender_district,\n" +
            "       es.sender_address    sender_address,\n" +
            "       er.receiver_name     receiver_name,\n" +
            "       er.receiver_phone    receiver_phone,\n" +
            "       er.receiver_province receiver_province,\n" +
            "       er.receiver_city     receiver_city,\n" +
            "       er.receiver_district receiver_district,\n" +
            "       er.receiver_address  receiver_address,\n" +
            "       e.status             status,\n" +
            "       e.type               type,\n" +
            "       e.weight             weight,\n" +
            "       ed.order_time        order_time\n" +
            "from express e\n" +
            "         left outer join express_sender es on e.express_id = es.express_id\n" +
            "         left outer join express_receiver er on e.express_id = er.express_id\n" +
            "         left outer join express_date ed on e.express_id = ed.express_id\n" +
            "where (e.courier_id =#{uid} and e.status = #{status}) and (e.status = #{status} and e.delivery_id = #{uid})")
    public List<Express> getAllSelfExpressesWithStatus(int uid, String status);

    // 修改快递状态
    @Update("update express set status = #{status} where express_id = #{expressId}")
    public int updateExpressStatus(String expressId, String status);
}
