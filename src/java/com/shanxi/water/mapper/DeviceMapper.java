package com.shanxi.water.mapper;

import com.shanxi.water.entity.DeviceInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeviceMapper {

    @Insert("INSERT INTO device_info(id,city_name, device_name,device_status, device_maintenance_status) " +
            "VALUES (" +
            "#{id}, " +
            "#{cityName},"+
            "#{deviceName}, " +
            "#{deviceStatus}, " +
            "#{deviceMaintenanceStatus})")
    void insertDeviceInfo(DeviceInfo deviceInfo);

    @Select("SELECT id, city_name, device_name, device_status, device_maintenance_status FROM device_info WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "deviceName", column = "device_name"),
            @Result(property = "deviceStatus", column = "device_status"),
            @Result(property = "deviceMaintenanceStatus", column = "device_maintenance_status"),
            @Result(property = "cityName", column = "city_name")
    })
    List<DeviceInfo> findDevicesByCityName(String cityName);

    //根据设备名称修改其他属性的方法
    @Update("UPDATE device_info SET " +
            "device_name = #{deviceName}, " +
            "device_status = #{deviceStatus}, " +
            "device_maintenance_status = #{deviceMaintenanceStatus} " +
            "WHERE id = #{id}")
    void updateDeviceInfoByDeviceId(DeviceInfo device);
    @Delete("DELETE FROM device_info WHERE id = #{id}")
    void deleteDeviceInfoById(@Param("id") String    id);
}
