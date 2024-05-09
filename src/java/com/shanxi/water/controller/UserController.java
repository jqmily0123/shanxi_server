package com.shanxi.water.controller;

import com.shanxi.water.entity.User;
import com.shanxi.water.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/users")
    public List<User> getUsers(){
       return userMapper.getUsers();
    }
    @DeleteMapping("delete_user/{id}")
    public void deleteUser(@PathVariable String id){
        userMapper.deleteUser(id);
    }
}
