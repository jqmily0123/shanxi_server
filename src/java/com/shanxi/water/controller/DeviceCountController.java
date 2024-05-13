package com.shanxi.water.controller;

import com.shanxi.water.entity.DeviceCount;
import com.shanxi.water.entity.DeviceInfo;
import com.shanxi.water.mapper.DeviceCountMapper;
import com.shanxi.water.mapper.DeviceMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeviceCountController {
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping("/devicecount/{cityname}")
    public DeviceCount getDeviceCount(@PathVariable String cityname){

        DeviceCount deviceCount = new DeviceCount();
        String cCityName = cityUrlMap.getChineseByPinyin(cityname);

        deviceCount.setOfflineDevicesCount(deviceMapper.countDevicesByCityAndStatus(cCityName,"离线"));
        deviceCount.setOnlineDevicesCount(deviceMapper.countDevicesByCityAndStatus(cCityName,"运行"));
        deviceCount.setWarningDevicesCount(deviceMapper.countDevicesByCityAndStatus(cCityName,"预警"));
        deviceCount.setDefaultDevicesCount(deviceMapper.countDevicesByCityAndStatus(cCityName,"故障"));
        return deviceCount;
    }
}
