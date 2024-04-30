package com.shanxi.water.mapper;

import com.shanxi.water.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(id,username, password) VALUES(#{id},#{username},#{password})")
    void insertUser(User user);
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);
    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(int id);
    @Update("UPDATE user SET username = #{username}, password = #{password} WHERE id = #{id}")
    void updateUser(User user);
}
