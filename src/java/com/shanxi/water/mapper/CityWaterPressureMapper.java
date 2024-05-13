package com.shanxi.water.mapper;

import com.shanxi.water.entity.CityWaterPressure;
import org.apache.ibatis.annotations.*;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CityWaterPressureMapper {
    @Select("SELECT * FROM city_water_pressure WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "coldWaterPressure", column = "cold_water_pressure"),
            @Result(property = "hotWaterPressure", column = "hot_water_pressure"),
            @Result(property = "installationDate", column = "installation_date")
    })
    List<CityWaterPressure> findByCityName(@Param("cityName") String cityName);

    @Select("SELECT * FROM city_water_pressure WHERE city_name = #{cityName} AND installation_date = #{installationDate}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "coldWaterPressure", column = "cold_water_pressure"),
            @Result(property = "hotWaterPressure", column = "hot_water_pressure"),
            @Result(property = "installationDate", column = "installation_date")
    })
    CityWaterPressure findByCityNameAndDate(@Param("cityName") String cityName, @Param("installationDate") LocalDate installationDate);

    // 插入方法
    @Insert("INSERT INTO city_water_pressure (id, city_name, cold_water_pressure, hot_water_pressure, installation_date) " +
            "VALUES (#{id}, #{cityName}, #{coldWaterPressure}, #{hotWaterPressure}, #{installationDate})")
    void insertCityWaterPressure(CityWaterPressure cityWaterPressure);


}
