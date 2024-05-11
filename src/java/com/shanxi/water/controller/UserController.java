package com.shanxi.water.controller;

import com.shanxi.water.entity.User;
import com.shanxi.water.mapper.UserMapper;
import com.shanxi.water.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordUtils passwordUtils;
    @GetMapping("/users")
    public List<User> getUsers(){
       return userMapper.getUsers();
    }
    @DeleteMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable String id){
        userMapper.deleteUser(id);
    }
    @PostMapping("/updateuser")
    public void updateUser(@RequestBody User user){
        User DUser = userMapper.getUserById(user.getId());
        if(user.getPassword()==null){
            user.setPassword(DUser.getPassword());
        }else{
            user.setPassword(passwordUtils.encode(user.getPassword()));
        }
        if(DUser.getUsername().equals(user.getUsername())){
            user.setUsername(DUser.getUsername());
        }
        if(user.getAvatar()==null){
            user.setAvatar(DUser.getAvatar());
        }
        userMapper.updateUser(user);
    }

    @GetMapping("/user/{username}")
    public User getUserByName(@PathVariable String username){
       User user = userMapper.getUserByUsername(username);
       System.out.println(user);
       return user;
    }
}
