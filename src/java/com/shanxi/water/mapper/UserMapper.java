package com.shanxi.water.mapper;

import com.shanxi.water.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(user_id,username, password) VALUES(#{UserId},#{username},#{password})")
    void insertUser(User user);
}
