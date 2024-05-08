package com.shanxi.water.generatedata;

import com.shanxi.water.entity.DeviceCount;
import com.shanxi.water.mapper.DeviceCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
@Component
public class GDeviceCount {
    @Autowired
    DeviceCountMapper deviceCountMapper;
    public void generateDeviceCount(){
    }
}
