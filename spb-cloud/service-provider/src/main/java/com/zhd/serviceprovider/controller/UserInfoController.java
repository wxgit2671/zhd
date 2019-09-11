package com.zhd.serviceprovider.controller;
/**
 * This file created by wangxin on 2019/8/28.
 */

import com.zhd.serviceprovider.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxin
 * @classDescription 用户控制相关
 * @create 2019-08-28 14:08
 **/
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping("/info")
    public String getUser(){
        return userInfoService.getUser();
    }
}
