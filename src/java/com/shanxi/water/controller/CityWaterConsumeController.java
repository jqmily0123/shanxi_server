package com.shanxi.water.controller;

import com.shanxi.water.entity.CityWaterConsume;
import com.shanxi.water.entity.CityWaterConsume;
import com.shanxi.water.mapper.CityWaterConsumeMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/citywaterconsume")
public class CityWaterConsumeController {
    @Autowired
    private CityWaterConsumeMapper cityWaterConsumeMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping(value = "/infos/{cityName}")
    public List<CityWaterConsume> getCityWaterConsume(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return cityWaterConsumeMapper.findByCityName(ccityName);
    }
    @GetMapping(value = "/avg/{cityName}")
    public  List<HashMap<String, Number>> getCityWaterConsumeByDate(@PathVariable String cityName) {
        LocalDate startDate = LocalDate.now().minusDays(365);
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        List<HashMap<String, Number>> res = new ArrayList<>();
        List<CityWaterConsume> list = new ArrayList<>();

        while (startDate.isBefore(LocalDate.now())) {
            if(startDate.isBefore(startDate.with(TemporalAdjusters.lastDayOfMonth()))){
                CityWaterConsume cityWaterConsume = cityWaterConsumeMapper.findByCityNameAndDate(ccityName,startDate);
                list.add(cityWaterConsume);
            }else{
                int month = startDate.getMonthValue();
                double totalHotWater = 0;
                double totalColdWater = 0;
                for (CityWaterConsume consumption : list) {
                    totalHotWater += consumption.getHotWaterConsume();  // Assuming getHotWater() returns the hot water amount
                    totalColdWater += consumption.getColdWaterConsume(); // Assuming getColdWater() returns the cold water amount
                }
                int numberOfDays = list.size();
                double avgHotWater = numberOfDays > 0 ? totalHotWater / numberOfDays : 0;
                double avgColdWater = numberOfDays > 0 ? totalColdWater / numberOfDays : 0;
                HashMap<String, Number> avgData = new HashMap<>();
                avgData.put("avgHotWaterConsume", Double.parseDouble(String.format("%.2f", avgHotWater)));
                avgData.put("avgColdConsume", Double.parseDouble(String.format("%.2f", avgColdWater)));
                avgData.put("month",month);
                res.add(avgData);
                list.clear();
            }
            startDate = startDate.plusDays(1);
        }
        return res;
    }
}
