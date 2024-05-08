package com.shanxi.water.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DeviceInfo {
    private String id;
    private String cityName;
    private String deviceName;
    private String deviceStatus;
    private String deviceMaintenanceStatus;
}
