package com.wxtest.demo.controller;
/**
 * This file created by wangxin on 2019/4/19.
 */

import com.google.gson.Gson;

import com.wxtest.demo.dao.UserDao;
import com.wxtest.demo.entity.UserInfoXS;
import com.wxtest.demo.service.TestService;
import com.wxtest.demo.utils.RedisUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxin
 * @classDescription 测试
 * @create 2019-04-19 10:54
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    Logger logger= LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestService testService;
    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping("/hello")
    public String a() {
        System.out.println("world");
        return "world" + testService.test();
    }

    @RequestMapping("/user")
    public UserInfoXS getUserInfo(String userId) {
        UserInfoXS userInfoXS;
        String value = redisUtils.getValueByKey("testwx");
        if (StringUtils.isNotBlank(value)) {
            logger.info("userId:{}命中缓存",userId);
            return new Gson().fromJson(value, UserInfoXS.class);
        } else {
            userInfoXS = testService.getUserInfo("3606945");
            redisUtils.putStringKeyValue("testwx", new Gson().toJson(userInfoXS));
        }
        return userInfoXS;
    }

    @RequestMapping("/user/master")
    public UserInfoXS getMasterUserInfo() {
        return testService.getUserInfoByMaster("3606945");
    }

    @RequestMapping("/user/insert")
    public String saveUserInfo() {
         testService.insertUserinfos();
         return "插入成功";
    }
}
