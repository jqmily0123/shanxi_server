package com.shanxi.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityWaterTemperature {
    private String id;
    private String cityName;
    private String month;
    private int coldWaterTemperature;
    private int hotWaterTemperature;
}
