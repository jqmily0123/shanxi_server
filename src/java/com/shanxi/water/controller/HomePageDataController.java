package com.shanxi.water.controller;

import com.shanxi.water.entity.DeviceInfo;
import com.shanxi.water.entity.HomePageData;
import com.shanxi.water.mapper.DeviceMapper;
import com.shanxi.water.mapper.HomePageSMapper;
import com.shanxi.water.mapper.PowerConsumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageDataController {
    @Autowired
    HomePageSMapper homePageSMapper;
    @Autowired
    PowerConsumeMapper powerConsumeMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @GetMapping("/home")
    public HomePageData getHomePageData() {
        HomePageData homePageData = new HomePageData();
        homePageData.setHomePageS(homePageSMapper.findFirstHomePageS());
        homePageData.setPowerConsumes(powerConsumeMapper.findAll());
        homePageData.setDeviceInfos(deviceMapper.findDevicesByCityName("西安市"));
        return homePageData;
    }
}
