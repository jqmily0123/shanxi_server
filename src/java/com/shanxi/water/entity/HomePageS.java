package com.shanxi.water.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomePageS {
    private String id;
    private double hotWaterVolume;
    private double coldWaterVolume;
    private int hotWaterTemperature;
    private int coldWaterTemperature;
    private int hotWaterPressure;
    private int coldWaterPressure;
}
