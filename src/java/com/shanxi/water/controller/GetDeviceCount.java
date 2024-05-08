package com.shanxi.water.controller;

import com.shanxi.water.entity.DeviceCount;
import com.shanxi.water.mapper.DeviceCountMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDeviceCount {
    @Autowired
    DeviceCountMapper deviceCountMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping("/devicecount")
    public DeviceCount getDeviceCount(){
        DeviceCount deviceCount =  deviceCountMapper.getOneDeviceCount();
        return deviceCount;
    }
}
