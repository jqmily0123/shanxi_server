package com.shanxi.water.controller;

import com.shanxi.water.entity.CityWaterTemperature;
import com.shanxi.water.mapper.CityWaterTemperatureMapper;
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
@RequestMapping("/citywatertemperature")
public class CityWaterTemperatureController {
    @Autowired
    CityWaterTemperatureMapper cityWaterTemperatureMapper;
    @Autowired
    private CityUrlMap cityUrlMap;

    @GetMapping("/infos/{cityname}")
    public List getAvgTemplate(@PathVariable String cityname) {
        String ccityname = cityUrlMap.getChineseByPinyin(cityname);
        List<CityWaterTemperature> temperatures = cityWaterTemperatureMapper.findByCityName(ccityname);
        return temperatures;
    }
    @GetMapping(value = "/avg/{cityName}")
    public  List<HashMap<String, Number>> getCityWaterConsume(@PathVariable String cityName) {
        LocalDate startDate = LocalDate.now().minusDays(365);
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        List<HashMap<String, Number>> res = new ArrayList<>();
        List<CityWaterTemperature> list = new ArrayList<>();

        while (startDate.isBefore(LocalDate.now())) {
            if(startDate.isBefore(startDate.with(TemporalAdjusters.lastDayOfMonth()))){
                CityWaterTemperature cityWaterTemperature = cityWaterTemperatureMapper.findByCityNameAndDate(ccityName,startDate);
//                System.out.println(cityWaterTemperature);
                list.add(cityWaterTemperature);
            }else{
                int month = startDate.getMonthValue();
                double totalHotWater = 0;
                double totalColdWater = 0;
                for (CityWaterTemperature consumption : list) {
                    totalHotWater += consumption.getHotWaterTemperature();  // Assuming getHotWater() returns the hot water amount
                    totalColdWater += consumption.getColdWaterTemperature(); // Assuming getColdWater() returns the cold water amount
                }
                int numberOfDays = list.size();
                double avgHotWater = numberOfDays > 0 ? totalHotWater / numberOfDays : 0;
                double avgColdWater = numberOfDays > 0 ? totalColdWater / numberOfDays : 0;
                HashMap<String, Number> avgData = new HashMap<>();
                avgData.put("avgHotWaterTemperature", Double.parseDouble(String.format("%.2f", avgHotWater)));
                avgData.put("avgColdWaterTemperature", Double.parseDouble(String.format("%.2f", avgColdWater)));
                avgData.put("month",month);
                res.add(avgData);
                list.clear();
            }
            startDate = startDate.plusDays(1);
        }
        return res;
    }
}
