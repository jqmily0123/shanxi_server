package com.shanxi.water.mapper;

import com.shanxi.water.entity.HomePageS;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HomePageSMapper {
    // 插入新的 HomePageS 记录，包括所有字段
    @Insert("INSERT INTO home_page_s (" +
            "id, " +
            "hot_water_volume, " +
            "cold_water_volume, " +
            "hot_water_temperature, " +
            "cold_water_temperature, " +
            "hot_water_pressure, " +
            "cold_water_pressure" +
            ") VALUES (" +
            "#{id}, " +
            "#{hotWaterVolume}, " +
            "#{coldWaterVolume}, " +
            "#{hotWaterTemperature}, " +
            "#{coldWaterTemperature}, " +
            "#{hotWaterPressure}, " +
            "#{coldWaterPressure})"
    )
    int insertHomePageS(HomePageS homePageS);

    // 随机查询第一条 HomePageS 记录
    @Select("SELECT id, hot_water_volume, cold_water_volume, hot_water_temperature, cold_water_temperature, hot_water_pressure, cold_water_pressure " +
            "FROM home_page_s " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "hotWaterVolume", column = "hot_water_volume"),
            @Result(property = "coldWaterVolume", column = "cold_water_volume"),
            @Result(property = "hotWaterTemperature", column = "hot_water_temperature"),
            @Result(property = "coldWaterTemperature", column = "cold_water_temperature"),
            @Result(property = "hotWaterPressure", column = "hot_water_pressure"),
            @Result(property = "coldWaterPressure", column = "cold_water_pressure")
    })
    HomePageS findRandomHomePageS();


}
