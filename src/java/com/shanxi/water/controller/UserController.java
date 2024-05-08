package com.shanxi.water.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/user")
    public String jiao(){

        return "注册成功";
    }
}
