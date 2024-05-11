package com.shanxi.water.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomePageS {
    private String id;
    private double hotWaterVolume;
    private double coldWaterVolume;
    private double hotWaterTemperature;
    private double coldWaterTemperature;
    private double hotWaterPressure;
    private double coldWaterPressure;
    private double hotWaterHumidity;
}
