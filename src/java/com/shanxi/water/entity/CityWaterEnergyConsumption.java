package com.shanxi.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CityWaterEnergyConsumption {
    private String id;
    private String cityName;
    private String month;
    private int coldWaterEnergyConsumption;
    private int hotWaterEnergyConsumption;
}
