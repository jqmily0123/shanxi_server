package com.shanxi.water.mapper;

import com.shanxi.water.entity.CityWaterEnergyConsumption;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CityWaterEnergyConsumptionMapper {
    @Select("SELECT * FROM city_water_energy_consumption WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "coldWaterEnergyConsumption", column = "cold_water_energy_consumption"),
            @Result(property = "hotWaterEnergyConsumption", column = "hot_water_energy_consumption"),
            @Result(property = "installationDate", column = "installation_date")
    })
    List<CityWaterEnergyConsumption> findByCityName(@Param("cityName") String cityName);

    @Select("SELECT * FROM city_water_energy_consumption WHERE city_name = #{cityName} AND installation_date = #{installationDate}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "coldWaterEnergyConsumption", column = "cold_water_energy_consumption"),
            @Result(property = "hotWaterEnergyConsumption", column = "hot_water_energy_consumption"),
            @Result(property = "installationDate", column = "installation_date")
    })
    CityWaterEnergyConsumption findByCityNameAndDate(@Param("cityName") String cityName,@Param("installationDate") LocalDate installationDate);

    // 插入方法
    @Insert("INSERT INTO city_water_energy_consumption (id, city_name, cold_water_energy_consumption, hot_water_energy_consumption, installation_date) " +
            "VALUES (#{id}, #{cityName}, #{coldWaterEnergyConsumption}, #{hotWaterEnergyConsumption}, #{installationDate})")
    void insertCityWaterEnergyConsumption(CityWaterEnergyConsumption cityWaterEnergyConsumption);
}
