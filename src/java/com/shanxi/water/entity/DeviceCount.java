package com.shanxi.water.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DeviceCount {
    private String id;
    private int offlineDevicesCount;
    private int onlineDevicesCount;
    private int defaultDevicesCount;
    private int warningDevicesCount;
    private int totalDevicesCount;

    public int getTotalDevicesCount() {
        return offlineDevicesCount+onlineDevicesCount+defaultDevicesCount+warningDevicesCount;
    }
}
