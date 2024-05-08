package com.shanxi.water.mapper;

import com.shanxi.water.entity.CityWaterEnergyConsumption;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityWaterEnergyConsumptionMapper {
    @Insert("INSERT INTO city_water_energy_consumption (id, city_name, month, cold_water_energy_consumption, hot_water_energy_consumption) " +
            "VALUES (#{id}, #{cityName}, #{month}, " +
            "#{coldWaterEnergyConsumption}, #{hotWaterEnergyConsumption})")
    void insertCityWaterEnergyConsumption(CityWaterEnergyConsumption cityWaterEnergyConsumption);

    @Select("SELECT id, city_name, month, cold_water_energy_consumption, hot_water_energy_consumption FROM city_water_energy_consumption WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "month", column = "month"),
            @Result(property = "coldWaterEnergyConsumption", column = "cold_water_energy_consumption"),
            @Result(property = "hotWaterEnergyConsumption", column = "hot_water_energy_consumption")
    })
    List<CityWaterEnergyConsumption> findByCityName(String cityName);
}
