package com.wx.zhd.spbshiro.dao;

import com.wx.zhd.spbshiro.entity.LoginLog;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:LoginLogDao
 * @Dscrription:登陆日志数据操作类
 */
@Repository
public interface LoginLogDao {
	LoginLog findById(Long id);
	List<LoginLog> findAll(Map<String, Object> condition);
	int save(LoginLog loginLog);
}
