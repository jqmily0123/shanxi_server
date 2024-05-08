package com.shanxi.water.controller;

import com.shanxi.water.common.BaseResponse;
import com.shanxi.water.entity.User;
import com.shanxi.water.mapper.UserMapper;
import com.shanxi.water.utils.PasswordUtils;
import com.shanxi.water.utils.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class LoginController {
    @Autowired
    PasswordUtils passwordUtils;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RegistrationValidator registrationValidator;
    @PostMapping("/register")
    public BaseResponse register(@RequestBody User user) {
        //1.验证前端传递过来的表单数据是否正常
        if(!registrationValidator.isValidUsername(user.getUsername())
                && !registrationValidator.isValidPassword(user.getPassword())
        ) {
            return new BaseResponse(400,"用户传递参数不正确");
        }
        //2.查看用户名是否已经存在 //如果存在就给他报错信息让在前端显示
        System.out.println(null!=userMapper.getUserByUsername(user.getUsername()));
        if(null!=userMapper.getUserByUsername(user.getUsername())){
            return new BaseResponse(400,"用户已存在");
        }
        //3.给密码加密以后写入到数据库中
        user.setPassword(passwordUtils.encode(user.getPassword()));
        userMapper.insertUser(user);
        return new BaseResponse(200,user,"注册成功");
    }
    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user) throws NoSuchAlgorithmException {
        User loginUser = userMapper.getUserByUsername(user.getUsername());
        if (null == loginUser || loginUser.getPassword() == null || user.getPassword() == null) {
            return new BaseResponse(401, "用户名或者密码不正确");
        } else if (!passwordUtils.comparePassword(user.getPassword(),loginUser.getPassword())) {
            return new BaseResponse(401, "用户名或者密码不正确");
        }
        return new BaseResponse(200,"用户登陆成功");
    }
}
