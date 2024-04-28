package com.shanxi.water;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@MapperScan
public class SpringbootApplication {
    public static void main(String[] args) {
        run(SpringbootApplication.class, args);
    }
}
