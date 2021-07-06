package com.dquav.dquav_platform;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author trex
 *
 */
@SpringBootApplication
@MapperScan("com.dquav.dquav_platform.mapper")
public class DquavPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(DquavPlatformApplication.class, args);
    }

}
