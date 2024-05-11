package com.shanxi.water.utils;
import org.springframework.stereotype.Component;

import java.util.regex.*;
@Component
public class RegistrationValidator {
    public boolean isValidUsername(String username) {
        //用户名可以包括字母、中文字符、下划线和数字，并且长度在2到10位之间
        String regex = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]{2,10}$";
        return Pattern.matches(regex, username);
    }
    public boolean isValidPassword(String password) {
        // 密码必须是小写字母或大写字母加数字组合 长度为6-20位
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
        return Pattern.matches(regex, password);
    }
}
