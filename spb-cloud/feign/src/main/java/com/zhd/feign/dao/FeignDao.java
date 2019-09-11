package com.zhd.feign.dao;
/**
 * This file created by wangxin on 2019/8/28.
 */

import org.springframework.stereotype.Repository;

/**
 * @author wangxin
 * @classDescription 消费者接口
 * @create 2019-08-28 16:05
 **/
@Repository
public interface FeignDao {

    String getMessage(String message);

    String selectAllUser();


}
