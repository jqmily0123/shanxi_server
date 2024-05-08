package com.shanxi.water.mapper;

import com.shanxi.water.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //插入用户信息
    @Insert("INSERT INTO user(id,username, password) VALUES(#{id},#{username},#{password})")
    void insertUser(User user);
    //根据ID获取用户信息
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);
    //根据姓名获取用户信息
    @Select("select * from user where username = #{username}")
    User getUserByUsername(@Param("username") String username);
    //根据用户id删除用户
    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(int id);
    //根据id修改用户信息
    @Update("UPDATE user SET username = #{username}, password = #{password} WHERE id = #{id}")
    void updateUser(User user);
}
