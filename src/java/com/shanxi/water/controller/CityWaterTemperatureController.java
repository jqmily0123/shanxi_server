package com.shanxi.water.controller;

import com.shanxi.water.entity.CityWaterTemperature;
import com.shanxi.water.mapper.CityWaterTemperatureMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citywatertemperature")
public class CityWaterTemperatureController {
    @Autowired
    CityWaterTemperatureMapper cityWaterTemperatureMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping(value = "/{cityName}")
    public List<CityWaterTemperature> getCityWaterTemperature(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return cityWaterTemperatureMapper.findByCityName(ccityName);
    }
}
