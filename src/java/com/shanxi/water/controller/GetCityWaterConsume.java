package com.shanxi.water.controller;

import com.shanxi.water.entity.CityWaterConsume;
import com.shanxi.water.mapper.CityWaterConsumeMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citywaterconsume")
public class GetCityWaterConsume {
    @Autowired
    private CityWaterConsumeMapper cityWaterConsumeMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping(value = "/{cityName}")
    public List<CityWaterConsume> getCityWaterConsume(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return cityWaterConsumeMapper.findByCityName(ccityName);
    }
}
