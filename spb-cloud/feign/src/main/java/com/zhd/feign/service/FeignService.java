package com.zhd.feign.service;
/**
 * This file created by wangxin on 2019/8/28.
 */

import com.zhd.feign.dao.FeignDao;
import com.zhd.feign.service.hystrix.FeignHystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @author wangxin
 * @classDescription 消费者逻辑处理
 * @create 2019-08-28 16:03
 **/
@Service
@FeignClient(value = "provider",fallback = FeignHystrix.class)
public class FeignService implements FeignDao {

    @Override
    public String getMessage(String message) {
        return null;
    }

    @Override
    public String selectAllUser() {
        return null;
    }
}
