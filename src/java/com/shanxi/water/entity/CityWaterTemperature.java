package com.shanxi.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CityWaterTemperature {
    private String id;
    private String cityName;
    private double coldWaterTemperature;
    private double hotWaterTemperature;
    private LocalDate installationDate;
}
