package com.shanxi.water.controller;
import com.shanxi.water.entity.DeviceInfo;
import com.shanxi.water.mapper.DeviceMapper;
import com.shanxi.water.utils.CityUrlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/citydevice")
@RestController
public class GetDeviceInfo {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private CityUrlMap cityUrlMap;
    @GetMapping("/{cityName}")
    public List<DeviceInfo> getCityDevice(@PathVariable String cityName) {
        String ccityName = cityUrlMap.getChineseByPinyin(cityName);
        return deviceMapper.findDevicesByCityName(ccityName);
    }
}
