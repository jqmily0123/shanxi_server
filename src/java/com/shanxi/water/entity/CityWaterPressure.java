package com.shanxi.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CityWaterPressure {
    private String id;
    private String cityName;
    private double coldWaterPressure;
    private double hotWaterPressure;
    private LocalDate installationDate;
}
