package com.shanxi.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CityWaterConsume {
    private String id;
    private String cityName;
    private String month;
    private int coldWaterConsume;
    private int hotWaterConsume;
}
