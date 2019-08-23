package com.wx.zhd.spbshiro.service;

import com.wx.zhd.spbshiro.dao.LoginLogDao;
import com.wx.zhd.spbshiro.entity.LoginLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginLogService {
    private static final Logger logger = LoggerFactory.getLogger(LoginLogService.class);
    /**
     * @Dscrription:登陆日志数据操作类
     */
    @Autowired
    private LoginLogDao loginLogDao;

    public LoginLog findById(Long id) {
        return loginLogDao.findById(id);
    }

    public List<LoginLog> findAll(Map<String, Object> condition) {
        return loginLogDao.findAll(condition);
    }

    public boolean save(LoginLog loginLog) {
        boolean flag = false;
        try {
            loginLogDao.save(loginLog);
            flag = true;
        } catch (Exception e) {
            logger.error("登陆日志出错name--" + loginLog.getUserName(), e);
        }
        return flag;
    }
}
