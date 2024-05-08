package com.shanxi.water.mapper;

import com.shanxi.water.entity.CityWaterTemperature;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityWaterTemperatureMapper {
    @Insert("INSERT INTO city_water_temperature (id, city_name, month, cold_water_temperature, hot_water_temperature) " +
            "VALUES (#{id}, #{cityName}, #{month}, " +
            "#{coldWaterTemperature}, #{hotWaterTemperature})")
    void insertCityWaterTemperature(CityWaterTemperature cityWaterTemperature);
    @Select("SELECT id, city_name, month, cold_water_temperature, hot_water_temperature FROM city_water_temperature WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "month", column = "month"),
            @Result(property = "coldWaterTemperature", column = "cold_water_temperature"),
            @Result(property = "hotWaterTemperature", column = "hot_water_temperature")
    })
    List<CityWaterTemperature> findByCityName(@Param("cityName") String cityName);
}
