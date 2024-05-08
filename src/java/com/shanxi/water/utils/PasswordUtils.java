package com.shanxi.water.utils;


import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//这个方法用来对用户密码加密
@Component
public class PasswordUtils {
    public String encode(String password) {
       try {
           MessageDigest digest = MessageDigest.getInstance("SHA-256");

           // 添加密码到 MessageDigest
           digest.update(password.getBytes());

           // 获取加密后的字节数组
           byte[] hashedBytes = digest.digest();

           // 将字节数组转换为十六进制字符串
           StringBuilder stringBuilder = new StringBuilder();
           for (byte hashedByte : hashedBytes) {
               stringBuilder.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
           }
           return stringBuilder.toString();
       }catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       return null;
    }
    public boolean comparePassword(String userPassword, String storedPassword) throws NoSuchAlgorithmException {
        String hashedInput = encode(userPassword);
        return hashedInput.equals(storedPassword);
    }
}
