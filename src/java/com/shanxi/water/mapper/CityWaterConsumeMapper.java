package com.shanxi.water.mapper;

import com.shanxi.water.entity.CityWaterConsume;
import org.apache.ibatis.annotations.*;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CityWaterConsumeMapper {
    @Select("SELECT * FROM city_water_consume WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "coldWaterConsume", column = "cold_water_consume"),
            @Result(property = "hotWaterConsume", column = "hot_water_consume"),
            @Result(property = "installationDate", column = "installation_date")
    })
    List<CityWaterConsume> findByCityName(@Param("cityName") String cityName);

    @Select("SELECT * FROM city_water_consume WHERE city_name = #{cityName} AND installation_date = #{installationDate}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "coldWaterConsume", column = "cold_water_consume"),
            @Result(property = "hotWaterConsume", column = "hot_water_consume"),
            @Result(property = "installationDate", column = "installation_date")
    })
    CityWaterConsume findByCityNameAndDate(@Param("cityName") String cityName,@Param("installationDate") LocalDate installationDate);
    // 插入方法
    @Insert("INSERT INTO city_water_consume (id, city_name, cold_water_consume, hot_water_consume, installation_date) " +
            "VALUES (#{id}, #{cityName}, #{coldWaterConsume}, #{hotWaterConsume}, #{installationDate})")
    void insertCityWaterConsume(CityWaterConsume cityWaterConsume);
}
