package com.shanxi.water.mapper;

import com.shanxi.water.entity.CityWaterTemperature;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CityWaterTemperatureMapper {
    @Insert("INSERT INTO city_water_temperature (id, city_name, cold_water_temperature, hot_water_temperature, installation_date) " +
            "VALUES (#{id}, #{cityName}, #{coldWaterTemperature}, #{hotWaterTemperature}, #{installationDate})")
    void insertCityWaterTemperature(CityWaterTemperature cityWaterTemperature);


    @Select("SELECT id, city_name, cold_water_temperature, hot_water_temperature, installation_date FROM city_water_temperature WHERE city_name = #{cityName} ")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "coldWaterTemperature", column = "cold_water_temperature"),
            @Result(property = "hotWaterTemperature", column = "hot_water_temperature"),
            @Result(property = "installationDate", column = "installation_date")
    })
    List<CityWaterTemperature> findByCityName(@Param("cityName") String cityName);


    @Select("SELECT id, city_name, cold_water_temperature, hot_water_temperature, installation_date FROM city_water_temperature WHERE city_name = #{cityName} AND installation_date = #{installationDate}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "coldWaterTemperature", column = "cold_water_temperature"),
            @Result(property = "hotWaterTemperature", column = "hot_water_temperature"),
            @Result(property = "installationDate", column = "installation_date")
    })
    CityWaterTemperature findByCityNameAndDate(@Param("cityName") String cityName, @Param("installationDate") LocalDate installationDate);
}
