package com.wxtest.demo.dao;
/**
 * This file created by wangxin on 2019/4/19.
 */

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口文件注释(Interface file)
 *
 * @author wangxin
 * @interfaceDescription 测试
 * @create 2019-04-19 10:52
 **/
@Repository
public interface TestDao {

    List queryUserInfos();
}
