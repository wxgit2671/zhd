package com.zhd.feign.service.hystrix;
/**
 * This file created by wangxin on 2019/8/29.
 */

import com.zhd.feign.dao.FeignDao;

import org.springframework.stereotype.Service;

/**
 * @author wangxin
 * @classDescription 配置熔断器
 * @create 2019-08-29 10:18
 **/
@Service
public class FeignHystrix implements FeignDao {


    @Override
    public String getMessage(String message) {
        return message+"服务器故障请重试";
    }

    @Override
    public String selectAllUser() {
        return  "服务器故障请重试" ;
    }
}
