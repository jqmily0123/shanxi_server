package com.shanxi.water.controller;

import com.shanxi.water.entity.CityWaterEnergyConsumption;
import com.shanxi.water.entity.CityWaterPressure;
import com.shanxi.water.entity.CityWaterPressure;
import com.shanxi.water.mapper.CityWaterPressureMapper;
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
@RequestMapping("/citywaterpressure")
public class CityWaterPressureController {
    @Autowired
    CityWaterPressureMapper cityWaterPressureMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping(value = "/avg/{cityName}")
    public  List<HashMap<String, Number>> getCityWaterConsume(@PathVariable String cityName) {
        LocalDate startDate = LocalDate.now().minusDays(365);
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        List<HashMap<String, Number>> res = new ArrayList<>();
        List<CityWaterPressure> list = new ArrayList<>();

        while (startDate.isBefore(LocalDate.now())) {
            if(startDate.isBefore(startDate.with(TemporalAdjusters.lastDayOfMonth()))){
                CityWaterPressure cityWaterPressure = cityWaterPressureMapper.findByCityNameAndDate(ccityName,startDate);
                System.out.println(cityWaterPressure);
                list.add(cityWaterPressure);
            }else{
                int month = startDate.getMonthValue();
                double totalHotWater = 0;
                double totalColdWater = 0;
                for (CityWaterPressure consumption : list) {
                    totalHotWater += consumption.getHotWaterPressure();  // Assuming getHotWater() returns the hot water amount
                    totalColdWater += consumption.getColdWaterPressure(); // Assuming getColdWater() returns the cold water amount
                }
                int numberOfDays = list.size();

                double avgHotWater = numberOfDays > 0 ? totalHotWater / numberOfDays : 0;
                double avgColdWater = numberOfDays > 0 ? totalColdWater / numberOfDays : 0;
                HashMap<String, Number> avgData = new HashMap<>();
                avgData.put("avgHotWaterPressure", Double.parseDouble(String.format("%.2f", avgHotWater)));
                avgData.put("avgColdWaterPressure", Double.parseDouble(String.format("%.2f", avgColdWater)));
                avgData.put("month",month);
                res.add(avgData);
                list.clear();
            }
            startDate = startDate.plusDays(1);
        }
        return res;
    }

    @GetMapping(value = "/infos/{cityName}")
    public List<CityWaterPressure> getCityWaterConsumeByDate(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return cityWaterPressureMapper.findByCityName(ccityName);
    }
}
