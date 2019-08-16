package com.wxtest.demo.config.database;
/*
 * This file created by wangxin on 2019/5/8.
*/


import com.wxtest.demo.common.constants.DataSourceConstants;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/*
 * @author wangxin
 * @classDescription 事务管理
 * @create 2019-05-08 19:42
 */

/*@Configuration
public class TransactionConfig {

    public final static String MASTER_TX = "masterTx";

    public final static String SLAVE_TX = "slaveTx";

    @Primary
    @Bean(name = TransactionConfig.MASTER_TX)
    public DataSourceTransactionManager masterTransaction(@Qualifier("masterDataSource") DataSource masterDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(masterDataSource);
        return dataSourceTransactionManager;
    }

    @Bean(name = TransactionConfig.SLAVE_TX)
    public DataSourceTransactionManager slaveTransaction(@Qualifier("slaveDataSource")
            DataSource  slaveDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager =
                new DataSourceTransactionManager(slaveDataSource);
        return dataSourceTransactionManager;
    }
}*/
