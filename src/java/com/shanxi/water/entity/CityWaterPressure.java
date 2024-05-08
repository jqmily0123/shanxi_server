package com.shanxi.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityWaterPressure {
    private String id;
    private String cityName;
    private String month;
    private double coldWaterPressure;
    private double hotWaterPressure;
}
