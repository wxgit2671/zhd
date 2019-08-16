package com.wxtest.demo.dao;
/**
 * This file created by mengqingyi on 2018/7/1.
 */

import com.wxtest.demo.entity.SolrCallDetailsSimple;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口文件注释(Interface file)
 *
 * @author mengqingyi
 * @interfaceDescription hbase-contacts临时方案 查询X库contacts
 * @create 2018-07-01 22:32
 **/
@Repository
public interface HbaseContactsDao {

    /**
     * 汇总 mysql中的contacts表数据
     */
    Boolean add(@Param("solrCallDetailsSimplesList") List<SolrCallDetailsSimple> solrCallDetailsSimplesList);

    /**
     * 根据userId和phone删除数据
     *
     * @param userId 用户id
     * @param phone  用户电话
     */
    void deleteByCondition(@Param("userId") Long userId, @Param("phone") String phone);

    /**
     * 根据userId和phone查询数据
     *
     * @param userId 用户id
     * @param phone  用户电话
     */
    List<SolrCallDetailsSimple> query(@Param("userId") Long userId, @Param("phone") String phone,
            @Param("flag") Boolean flag);

    List<Long> queryUserToExecute();

}
