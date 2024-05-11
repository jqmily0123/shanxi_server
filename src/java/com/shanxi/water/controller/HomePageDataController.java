package com.shanxi.water.controller;

import com.shanxi.water.entity.DeviceInfo;
import com.shanxi.water.entity.HomePageData;
import com.shanxi.water.entity.HomePageS;
import com.shanxi.water.mapper.DeviceMapper;
import com.shanxi.water.mapper.HomePageSMapper;
import com.shanxi.water.mapper.PowerConsumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

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
        generateHomePageS();
        homePageData.setHomePageS(homePageSMapper.findRandomHomePageS());
        homePageData.setPowerConsumes(powerConsumeMapper.findAll());
        homePageData.setDeviceInfos(deviceMapper.findDevicesByCityName("西安市"));
        return homePageData;
    }
    private void generateHomePageS(){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        HomePageS homePageS = new HomePageS();
        homePageS.setId(UUID.randomUUID().toString());

        homePageS.setColdWaterVolume(Double.parseDouble(decimalFormat.format(0.65+(Math.random()-0.5)*0.1)));
        homePageS.setHotWaterVolume(Double.parseDouble(decimalFormat.format(0.85+(Math.random()-0.5)*0.1)));

        homePageS.setColdWaterTemperature(Double.parseDouble(decimalFormat.format(23+(Math.random()-0.5)*5)));
        homePageS.setHotWaterTemperature(Double.parseDouble(decimalFormat.format(93+(Math.random()-0.5)*5)));

        homePageS.setColdWaterPressure(Double.parseDouble(decimalFormat.format(2+(Math.random()-0.5))));
        homePageS.setHotWaterPressure(Double.parseDouble(decimalFormat.format(2+(Math.random()-0.5))));
        homePageSMapper.insertHomePageS(homePageS);
    }
}
