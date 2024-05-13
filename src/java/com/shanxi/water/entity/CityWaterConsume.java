package com.shanxi.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class CityWaterConsume {
    private String id;
    private String cityName;
    private double coldWaterConsume;
    private double hotWaterConsume;
    private LocalDate installationDate;
}
