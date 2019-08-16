package com.wx.zhd.spbshiro.config.database;
/**
 * This file created by wangxin on 2019/4/18.
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wangxin
 * @classDescription 动态数据源设置
 * @create 2019-04-18 18:33
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return DynamicDataSourceContextHolder.getDataSourceType();
        }
}
