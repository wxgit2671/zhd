package com.wxtest.demo.config.database;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * 修正MybtisAutoConfig的bug (配置config属性后其他属性会被忽略)
 */


@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class DynamicDataSourceRegister implements EnvironmentAware {

    Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    private Environment environment;
    @Autowired
    private MybatisProperties properties;
    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /*
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean
    public DataSource masterDataSource() throws Exception {
        logger.info("开始初始化数据库(master)配置...");
        return buildDataSource("spring.datasource.master");
    }

    @Bean
    public DataSource slaveDataSource() throws Exception {
        logger.info("开始初始化数据库(slave)配置...");
        return buildDataSource("spring.datasource.slave");
    }
    /*
    @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
    @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
    */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("slave", slaveDataSource);

        DynamicDataSourceContextHolder.dataSourceIds.add("master");
        DynamicDataSourceContextHolder.dataSourceIds.add("slave");
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(masterDataSource);// 默认的datasource设置为myTestDbDataSource
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        factory.setMapperLocations(this.properties.resolveMapperLocations());
        return factory.getObject();
    }
    /**
     * 构造数据源
     *
     * @param dataSourcePrefix 数据源前缀
     */
    private DataSource buildDataSource(String dataSourcePrefix) throws Exception {
        /**
         * 参数绑定工具 springboot2.0新推出
         */
        Binder binder = Binder.get(environment);
        Map defauleDataSourceProperties = binder.bind(dataSourcePrefix, Map.class).get();
        Properties props = new Properties();
        props.put("url", defauleDataSourceProperties.get("url"));
        props.put("driverClassName", defauleDataSourceProperties.get("driver-class-name"));
        props.put("username", ConfigTools.decrypt(defauleDataSourceProperties.get("username").toString()));
        props.put("password", ConfigTools.decrypt(defauleDataSourceProperties.get("password").toString()));
        props.put("maxActive", defauleDataSourceProperties.get("maxActive") + "");
        //		props.put("maxIdle", propertyResolver.getProperty("maxIdle"));
        props.put("minIdle", defauleDataSourceProperties.get("minIdle") + "");
        props.put("validation-query", defauleDataSourceProperties.get("validation-query"));
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
        dataSource.setFilters("stat,wall");
        dataSource.setUseGlobalDataSourceStat(true);
        logger.info("初始化数据库{}配置完成,url={},maxActive={},maxIdle={},minIdle={}", dataSourcePrefix,
                defauleDataSourceProperties.get("url"), dataSource.getMaxActive(), dataSource.getMaxIdle(),
                dataSource.getMinIdle());
        return dataSource;
    }

    @Test
    public void dataup() {
        try {
            System.out.println(ConfigTools.encrypt("Mimidai*2019"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
