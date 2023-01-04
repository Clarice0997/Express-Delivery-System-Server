package com.example.expressdeliverysystemserver.mapper;

import com.example.expressdeliverysystemserver.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    // 查询账号是否存在 Mapper
    @Select("select * from admin where username = #{username}")
    public Admin getUser(String username);

    // 查询账户ID Mapper
    @Select("select id from admin where username = #{username}")
    public int getUid(String username);

    // 查询对应账号密码 Mapper
    @Select("select password from admin where username = #{username}")
    public String getPassword(String username);

    // 查询账号类型 Mapper
    @Select("select type from admin where id = #{id}")
    public int getType(int id);

    // 查询账号状态 Mapper
    @Select("select status from admin where id = #{id}")
    public int getStatus(int id);
}
