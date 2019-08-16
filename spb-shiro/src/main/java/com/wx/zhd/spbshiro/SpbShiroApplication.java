package com.wx.zhd.spbshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.wx.zhd.spbshiro.dao")
public class SpbShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbShiroApplication.class, args);
    }

}
