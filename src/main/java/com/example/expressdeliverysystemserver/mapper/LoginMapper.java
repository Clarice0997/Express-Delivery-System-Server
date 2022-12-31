package com.example.expressdeliverysystemserver.mapper;

import com.example.expressdeliverysystemserver.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LoginMapper {
    // 查询账号是否存在 Mapper
    @Select("select * from users where username = #{username}")
    public Account getUser(String username);

    // 查询账户ID Mapper
    @Select("select uid from users where username = #{username}")
    public int getUid(String username);

    // 查询对应账号密码 Mapper
    @Select("select password from users where username = #{username}")
    public String getPassword(String username);

    // 注册 Mapper
    @Update("insert into users values(null,#{username},#{password},now(),now())")
    public int register(Account account);
}
