package com.wxtest.demo.service;
/**
 * This file created by wangxin on 2019/4/18.
 */

import com.wxtest.demo.common.constants.DataSourceConstants;
import com.wxtest.demo.config.database.TargetDataSource;
import com.wxtest.demo.dao.UserDao;
import com.wxtest.demo.dao.UserInfoXSDao;
import com.wxtest.demo.entity.User;
import com.wxtest.demo.entity.UserInfoXS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author wangxin
 * @classDescription 测试
 * @create 2019-04-18 18:28
 **/
@Service
public class TestService {
    @Autowired
    private UserInfoXSDao userInfoXSDao;
    @Autowired
    private UserDao userDao;

    public String test() {
        System.out.println("hello");
        return "hello";
    }

    @TargetDataSource(name = DataSourceConstants.SLAVE)
    public UserInfoXS getUserInfo(String userId) {
        return userInfoXSDao.getById(Long.parseLong(userId));
    }

    @TargetDataSource(name = DataSourceConstants.MASTER)
    public UserInfoXS getUserInfoByMaster(String userId) {
        return userInfoXSDao.getById(Long.parseLong(userId));
    }

    @TargetDataSource(name = DataSourceConstants.SLAVE)
    @Transactional()
    public void insertUserinfos() {
        UserInfoXS userInfoXS=new UserInfoXS();
        userInfoXS.setAddress("lllll");
        userInfoXS.setUserId(252555l);
        userInfoXSDao.insert(userInfoXS);
        User u =new User();
        u.setId(22);
        u.setPhone("1111111111");
        u.setAuditState("22");
        userDao.insert(u);
        //System.out.println(1/0);
    }
}
