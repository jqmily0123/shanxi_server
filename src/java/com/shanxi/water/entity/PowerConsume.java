package com.shanxi.water.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PowerConsume {
    private String id;
    private String equipment;
    private int power;
}
