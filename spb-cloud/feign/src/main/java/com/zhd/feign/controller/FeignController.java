package com.zhd.feign.controller;
/**
 * This file created by wangxin on 2019/8/28.
 */

import com.zhd.feign.service.FeignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxin
 * @classDescription 消费者
 * @create 2019-08-28 16:02
 **/
@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @RequestMapping("/user/getMessage" )
    public String GetMessage(@RequestParam("message") String message){
        return feignService.getMessage(message);
    }


    @RequestMapping("/user/info")
    public String SelectAllUser(){
        return feignService.selectAllUser();
    }
}
