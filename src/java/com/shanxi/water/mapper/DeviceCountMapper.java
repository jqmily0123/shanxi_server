package com.shanxi.water.mapper;

import com.shanxi.water.entity.DeviceCount;
import com.shanxi.water.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DeviceCountMapper {
    @Insert("INSERT INTO device_count(id,offline_devices_count, online_devices_count,warning_devices_count,shutdown_devices_count,total_devices_count) VALUES(#{id},#{offlineDevicesCount},#{onlineDevicesCount},#{warningDevicesCount},#{shutdownDevicesCount},#{totalDevicesCount})")
    void insertDeviceCount(DeviceCount deviceCount);

    @Select("SELECT id, offline_devices_count, online_devices_count, shutdown_devices_count, warning_devices_count, total_devices_count FROM device_count ORDER BY id ASC LIMIT 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "offlineDevicesCount", column = "offline_devices_count"),
            @Result(property = "onlineDevicesCount", column = "online_devices_count"),
            @Result(property = "shutdownDevicesCount", column = "shutdown_devices_count"),
            @Result(property = "warningDevicesCount", column = "warning_devices_count"),
            @Result(property = "totalDevicesCount", column = "total_devices_count")
    })
    DeviceCount getOneDeviceCount();
}

