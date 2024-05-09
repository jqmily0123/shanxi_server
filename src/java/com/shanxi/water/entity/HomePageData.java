package com.shanxi.water.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomePageData {
    private HomePageS homePageS;
    private List<PowerConsume> powerConsumes;
    private List<DeviceInfo> deviceInfos;
}
