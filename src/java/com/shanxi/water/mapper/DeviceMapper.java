package com.shanxi.water.mapper;

import com.shanxi.water.entity.DeviceInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.time.LocalDate;
@Mapper
public interface DeviceMapper {


    @Insert("INSERT INTO device_info (id, city_name, device_name, device_status, device_maintenance_status, installation_date) " +
            "VALUES (#{id}, #{cityName}, #{deviceName}, #{deviceStatus}, #{deviceMaintenanceStatus}, #{installationDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "city_name", property = "cityName"),
            @Result(column = "device_name", property = "deviceName"),
            @Result(column = "device_status", property = "deviceStatus"),
            @Result(column = "device_maintenance_status", property = "deviceMaintenanceStatus"),
            @Result(column = "installation_date", property = "installationDate")
    })
    void insertDeviceInfo(DeviceInfo deviceInfo);

    @Select("SELECT id, city_name, device_name, device_status, device_maintenance_status FROM device_info WHERE city_name = #{cityName} LIMIT 150")
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

    @Select("SELECT id, city_name, device_name, device_status, device_maintenance_status, installation_date FROM device_info WHERE city_name = #{cityName} AND installation_date = #{date}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "city_name", property = "cityName"),
            @Result(column = "device_name", property = "deviceName"),
            @Result(column = "device_status", property = "deviceStatus"),
            @Result(column = "device_maintenance_status", property = "deviceMaintenanceStatus"),
            @Result(column = "installation_date", property = "installationDate")
    })
    List<DeviceInfo> getDeviceInfoByCityNameAndDate(@Param("cityName") String cityName, @Param("date") LocalDate date);

    @Select("SELECT id, city_name, device_name, device_status, device_maintenance_status, installation_date FROM device_info WHERE city_name = #{cityName} AND installation_date = #{date} LIMIT 150")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "city_name", property = "cityName"),
            @Result(column = "device_name", property = "deviceName"),
            @Result(column = "device_status", property = "deviceStatus"),
            @Result(column = "device_maintenance_status", property = "deviceMaintenanceStatus"),
            @Result(column = "installation_date", property = "installationDate")
    })
    List<DeviceInfo> getSomeDeviceInfoByCityNameAndDate(@Param("cityName") String cityName, @Param("date") LocalDate date);

    @Delete("DELETE FROM device_info")
    void deleteAll();

    @Select("SELECT COUNT(*) FROM device_info WHERE city_name = #{cityName} AND device_status = #{deviceStatus}")
    int countDevicesByCityAndStatus(@Param("cityName") String cityName, @Param("deviceStatus") String deviceStatus);
}
