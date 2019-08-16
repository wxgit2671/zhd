package com.wxtest.demo.common.hbase;
/**
 * This file created by mengqingyi on 2018/7/18.
 */

/**
 * 接口文件注释(Interface file)
 *
 * @author mengqingyi
 * @interfaceDescription hbase存活接口状态
 * @create 2018-07-18 14:19
 **/
public class HbaseActiveStatus {

    /**
     * 设置一个静态常量 true为hbase正常运行 false为 切换至 mysql 设置默认值为 true 默认认为 hbase 正常（防止出现redis故障，导致数据切换至mysql）
     */
    public static Boolean HBASE_ACTIVE = Boolean.TRUE;

}
