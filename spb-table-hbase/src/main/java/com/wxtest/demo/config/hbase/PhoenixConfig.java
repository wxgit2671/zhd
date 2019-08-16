package com.wxtest.demo.config.hbase;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by 苦苦奋斗的渣渣 on 2017/5/25.
 */
@Configuration
public class PhoenixConfig {
    Logger logger= LoggerFactory.getLogger(PhoenixConfig.class);
    @Value("${phoenix_url}")
    private String phoenix_url;

    public BasicDataSource phoenixDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.apache.phoenix.jdbc.PhoenixDriver");
        dataSource.setInitialSize(0);
        dataSource.setMinIdle(0);
        dataSource.setMaxActive(50);
        dataSource.setMaxWait(5000);
        //暂时注释  借出连接时不要测试，否则很影响性能 默认是true
        //dataSource.setTestOnBorrow(false);
        dataSource.setTimeBetweenEvictionRunsMillis(1000);
        dataSource.setMinEvictableIdleTimeMillis(5000);
        String url = phoenix_url;
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public JdbcTemplate phoenixJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(phoenixDataSource());
        logger.info("hbase数据源初始化完成");
        return jdbcTemplate;
    }

}
