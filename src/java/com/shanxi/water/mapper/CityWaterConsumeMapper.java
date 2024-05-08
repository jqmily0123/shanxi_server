package com.shanxi.water.mapper;

import com.shanxi.water.entity.CityWaterConsume;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityWaterConsumeMapper {
    @Insert("INSERT INTO city_water_consume (id, city_name, month, cold_water_consume, hot_water_consume) " +
            "VALUES (#{id}, #{cityName}, #{month}, " +
            "#{coldWaterConsume}, #{hotWaterConsume})")
    void insertCityWaterConsume(CityWaterConsume cityWaterConsume);

    @Select("SELECT id, city_name, month, cold_water_consume, hot_water_consume FROM city_water_consume WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "month", column = "month"),
            @Result(property = "coldWaterConsume", column = "cold_water_consume"),
            @Result(property = "hotWaterConsume", column = "hot_water_consume")
    })
    List<CityWaterConsume> findByCityName(@Param("cityName") String cityName);
}
