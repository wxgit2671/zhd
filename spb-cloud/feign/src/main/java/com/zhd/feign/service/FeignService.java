package com.zhd.feign.service;
/**
 * This file created by wangxin on 2019/8/28.
 */

import com.zhd.feign.service.hystrix.FeignHystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangxin
 * @classDescription 消费者逻辑处理
 * @create 2019-08-28 16:03
 **/
@FeignClient(value = "provider",fallback = FeignHystrix.class)
public interface FeignService{

    @RequestMapping("/user/getService")
     String getMessage(@RequestParam("message") String message);

    @RequestMapping("/user/info")
     String selectAllUser() ;
}
