package com.shanxi.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class CityWaterEnergyConsumption {
    private String id;
    private String cityName;
    private double coldWaterEnergyConsumption;
    private double hotWaterEnergyConsumption;
    private LocalDate installationDate;
}
