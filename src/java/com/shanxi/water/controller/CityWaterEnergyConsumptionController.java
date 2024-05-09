package com.shanxi.water.controller;

import com.shanxi.water.entity.CityWaterEnergyConsumption;
import com.shanxi.water.mapper.CityWaterEnergyConsumptionMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citywaterenergyconsumption")
public class CityWaterEnergyConsumptionController {
    @Autowired
    private CityWaterEnergyConsumptionMapper  cityWaterEnergyConsumptionMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping(value = "/{cityName}")
    public List<CityWaterEnergyConsumption> getCityWaterConsume(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return cityWaterEnergyConsumptionMapper.findByCityName(ccityName);
    }
}
