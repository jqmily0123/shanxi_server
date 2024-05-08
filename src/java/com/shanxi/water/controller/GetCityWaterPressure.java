package com.shanxi.water.controller;

import com.shanxi.water.entity.CityWaterPressure;
import com.shanxi.water.mapper.CityWaterPressureMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citywaterpressure")
public class GetCityWaterPressure {
    @Autowired
    CityWaterPressureMapper cityWaterPressureMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping(value = "/{cityName}")
    public List<CityWaterPressure> getCityWaterPressure(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return cityWaterPressureMapper.findByCityName(ccityName);
    }
}
