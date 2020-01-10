package com.gl.police;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(
        "com.gl.police.daoAPI"
)
public class PoliceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoliceApplication.class, args);
    }

}
