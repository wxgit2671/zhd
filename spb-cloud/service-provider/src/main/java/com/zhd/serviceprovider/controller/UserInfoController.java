package com.zhd.serviceprovider.controller;
/**
 * This file created by wangxin on 2019/8/28.
 */

import com.zhd.serviceprovider.service.UserInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxin
 * @classDescription 用户控制相关
 * @create 2019-08-28 14:08
 **/
@RestController
@RequestMapping("/user")
public class UserInfoController {
    Logger logger= LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping("/info")
    public String getUser(){
        logger.info("查询用户数据");
        return userInfoService.getUser();
    }

    @RequestMapping(value ="/getService",method = RequestMethod.GET)
    public String GetService(@RequestParam(value = "message") String message){
        logger.info("message:{}",message);
        return message+"获取到服务";
    }
}
