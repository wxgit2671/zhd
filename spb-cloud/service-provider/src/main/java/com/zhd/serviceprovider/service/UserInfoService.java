package com.zhd.serviceprovider.service;
/**
 * This file created by wangxin on 2019/8/28.
 */

import com.zhd.serviceprovider.dao.user.UserInfoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangxin
 * @classDescription 用户相关
 * @create 2019-08-28 14:05
 **/
@Service
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    public  String getUser(){
       return userInfoDao.getByUserId(2L).getName();
    }
}
