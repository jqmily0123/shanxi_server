package com.shanxi.water.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/jiao")
    public String jiao(){
        return "12";
    }
}
