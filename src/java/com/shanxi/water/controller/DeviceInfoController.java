package com.shanxi.water.controller;
import com.shanxi.water.entity.DeviceInfo;
import com.shanxi.water.mapper.DeviceMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/citydevice")
@RestController
public class DeviceInfoController {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping("/{cityName}")
    public List<DeviceInfo> getCityDevice(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return deviceMapper.findDevicesByCityName(ccityName);
    }
    @PostMapping("/updateDevice")
    public com.shanxi.water.entity.DeviceInfo updateDevice(@RequestBody DeviceInfo deviceInfo) {
        deviceMapper.updateDeviceInfoByDeviceId(deviceInfo);
        return deviceInfo;
    }
    @DeleteMapping("/deleteDeviceInfo/{id}")
    public void deleteDeviceById(@PathVariable("id") String id) {
        deviceMapper.deleteDeviceInfoById(id);
    }

    @GetMapping("/{cityName}/{startDate}/{endDate}")
    public List<DeviceInfo> getDevicesByCityAndDateRange(
            @PathVariable("cityName") String cityName,
            @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        List<DeviceInfo> deviceInfos = new ArrayList<>();

        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            deviceInfos.addAll(deviceMapper.getDeviceInfoByCityNameAndDate(ccityName, current));
            current = current.plusDays(1);
        }
        return deviceInfos;
    }
}
