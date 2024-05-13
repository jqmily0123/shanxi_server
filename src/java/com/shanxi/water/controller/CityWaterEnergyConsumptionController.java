package com.shanxi.water.controller;

import com.shanxi.water.entity.CityWaterConsume;
import com.shanxi.water.entity.CityWaterEnergyConsumption;
import com.shanxi.water.mapper.CityWaterEnergyConsumptionMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.temporal.TemporalAdjusters;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/citywaterenergyconsumption")
public class CityWaterEnergyConsumptionController {
    @Autowired
    private CityWaterEnergyConsumptionMapper  cityWaterEnergyConsumptionMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping(value = "/avg/{cityName}")
    public  List<HashMap<String, Number>> getCityWaterConsume(@PathVariable String cityName) {
        LocalDate startDate = LocalDate.now().minusDays(365);
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        List<HashMap<String, Number>> res = new ArrayList<>();
        List<CityWaterEnergyConsumption> list = new ArrayList<>();

        while (startDate.isBefore(LocalDate.now())) {
            if(startDate.isBefore(startDate.with(TemporalAdjusters.lastDayOfMonth()))){
                CityWaterEnergyConsumption cityWaterEnergyConsumption = cityWaterEnergyConsumptionMapper.findByCityNameAndDate(ccityName,startDate);
                list.add(cityWaterEnergyConsumption);
            }else{
                int month = startDate.getMonthValue();
                double totalHotWater = 0;
                double totalColdWater = 0;
                for (CityWaterEnergyConsumption consumption : list) {
                    totalHotWater += consumption.getHotWaterEnergyConsumption();  // Assuming getHotWater() returns the hot water amount
                    totalColdWater += consumption.getColdWaterEnergyConsumption(); // Assuming getColdWater() returns the cold water amount
                }
                int numberOfDays = list.size();
                double avgHotWater = numberOfDays > 0 ? totalHotWater / numberOfDays : 0;
                double avgColdWater = numberOfDays > 0 ? totalColdWater / numberOfDays : 0;
                HashMap<String, Number> avgData = new HashMap<>();
                avgData.put("avgHotWaterEnergyConsumption", Double.parseDouble(String.format("%.2f", avgHotWater)));
                avgData.put("avgColdEnergyConsumption", Double.parseDouble(String.format("%.2f", avgColdWater)));

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

    @GetMapping(value = "/infos/{cityName}")
    public List<CityWaterEnergyConsumption> getCityWaterConsumeByDate(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return cityWaterEnergyConsumptionMapper.findByCityName(ccityName);
    }
}
