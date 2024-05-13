package com.shanxi.water.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DeviceInfo {
    private String id;
    private String cityName;
    private String deviceName;
    private String deviceStatus;  //这里的设备状态主要有 离线 运行 关闭 预警 四种状态
    private String deviceMaintenanceStatus;
    private LocalDate installationDate;
}
