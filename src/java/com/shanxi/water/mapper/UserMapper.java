package com.shanxi.water.mapper;

import com.shanxi.water.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(id,username, password) VALUES(#{id},#{username},#{password})")
    void insertUser(User user);
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);
}
