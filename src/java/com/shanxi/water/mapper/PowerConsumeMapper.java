package com.shanxi.water.mapper;

import com.shanxi.water.entity.PowerConsume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PowerConsumeMapper {
    @Insert("INSERT INTO power_consume (id, equipment, power) VALUES (#{id}, #{equipment}, #{power})")
    int insertPowerConsume(PowerConsume powerConsume);

    @Select("SELECT id, equipment, power FROM power_consume")
    List<PowerConsume> findAll();
}
