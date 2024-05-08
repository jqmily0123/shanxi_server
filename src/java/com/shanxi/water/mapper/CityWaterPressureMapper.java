package com.shanxi.water.mapper;

import com.shanxi.water.entity.CityWaterPressure;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityWaterPressureMapper {
    @Insert("INSERT INTO city_water_pressure (id, city_name, month, cold_water_pressure, hot_water_pressure) " +
            "VALUES (#{id}, #{cityName}, #{month}, " +
            "#{coldWaterPressure}, #{hotWaterPressure})")
    void insertCityWaterPressure(CityWaterPressure cityWaterPressure);
    @Select("SELECT id, city_name, month, cold_water_pressure, hot_water_pressure FROM city_water_pressure WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "month", column = "month"),
            @Result(property = "coldWaterPressure", column = "cold_water_pressure"),
            @Result(property = "hotWaterPressure", column = "hot_water_pressure")
    })
    List<CityWaterPressure> findByCityName(@Param("cityName") String cityName);
}
