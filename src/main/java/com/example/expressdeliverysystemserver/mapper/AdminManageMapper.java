package com.example.expressdeliverysystemserver.mapper;

import com.example.expressdeliverysystemserver.entity.Admin;
import com.example.expressdeliverysystemserver.entity.Courier;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminManageMapper {
    // 查询所有快递员（分页）
    @Select("select id,username,nickname,phone,status from admin where type = '2' limit #{pageNumber},#{pageSize}")
    public List<Courier> selectCourier(Integer pageNumber, Integer pageSize);

    // 查询快递员数据条数
    @Select("select count(id) from admin where type = '2'")
    public Integer selectCountCourier();

    // 根据ID查询快递员
    @Select("select id,username,nickname,phone,status from admin where type = '2' and id = #{id}")
    public Courier selectCourierById(Integer id);

    // 查询快递员是否已存在
    @Select("select username from admin where username = #{username}")
    public String isExistUsername(String username);

    // 查询电话号码是否已存在
    @Select("select phone from admin where phone = #{phone}")
    public String isExistPhone(String phone);

    // 插入快递员
    @Insert("insert into admin(username,password,nickname,phone,status,type,create_time,update_time) values(#{username},#{password},#{nickname},#{phone},'0','2',now(),now())")
    public Integer insertCourier(Admin admin);

    // 修改快递员状态
    @Update("update admin set status = #{status} where id = #{id}")
    public Integer updateCourierStatus(Admin admin);

    // 修改快递员信息
    @Update("update admin set nickname = #{nickname} , phone = #{phone} where username = #{username}")
    public Integer updateCourier(Admin admin);

    // 删除快递眼
    @Delete("delete from admin where id = #{id}")
    public Integer deleteCourier(Integer id);
}
