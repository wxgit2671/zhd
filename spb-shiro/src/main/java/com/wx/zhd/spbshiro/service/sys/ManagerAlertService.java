package com.wx.zhd.spbshiro.service.sys;


import com.google.common.collect.Maps;

import com.wx.zhd.spbshiro.dao.sys.ManagerAlertDao;
import com.wx.zhd.spbshiro.entity.sys.ManagerAlert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 
    * @ClassName: ManagerAlertService
    * @Description: TODO(系统提示信息service)
    * @date 2017年10月25日
    *
 */
@Service
public class ManagerAlertService {
	private static final Logger logger = LoggerFactory.getLogger(ManagerAlertService.class);
	@Autowired
	private ManagerAlertDao managerAlertDao;
	@Autowired
	private RedisService redisService;
	/**
	 * 
	    * @Title: getCountUnread
	    * @Description: (统计未读数量)
	    * @param @param managerId
	    * @param @return    参数
	    * @return Integer    返回类型
	    * @throws
	 */
	/*public Integer getCountUnread(Long managerId) {
		return managerAlertDao.getCountUnread(managerId);
	}*/
	public Integer getCountUnread(Long managerId) {
		//return managerAlertDao.getCountUnread(managerId);
		HashMap<String, Object> map=Maps.newHashMap();
		map.put("managerId", managerId);
		map.put("date",new Date());
		//return managerAlertDao.getCountUnreadNew(managerId,new Date());
		return managerAlertDao.getCountUnreadNew(map);
	}
	/**
	 * 
	    * @Title: getUnreadList
	    * @Description: (获取列表)
	    * @param @param managerId
	    * @param @return    参数
	    * @return List<Map<String,Object>>    返回类型
	    * @throws
	 */
	public List<ManagerAlert> getUnreadList(Map<String, Object> map) {
		return managerAlertDao.getUnreadList(map);
	}
	/**
	 * 
	    * @Title: setStateToRead
	    * @Description: (设置状态为已读状态)
	    * @param @param managerAlertId    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void setStateToRead(Long managerAlertId,Long managerId) {
		 managerAlertDao.setStateToRead(managerAlertId);
		 updateRedisManagerAlertCount(managerId);
	}
	/**
	 * 
	    * @Title: setStateToIgnore
	    * @Description: (设置某条记录 状态为忽略)
	    * @param @param managerAlertId    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void setStateToIgnore(Long managerAlertId,Long managerId) {
		managerAlertDao.setStateToIgnore(managerAlertId);
		updateRedisManagerAlertCount(managerId);
	}
	/**
	 * 
	    * @Title: getUnreadCodeKey
	    * @Description: (获取未读消息所有的key -用于类型列表展示)
	    * @param @param managerAlertId
	    * @param @return    参数
	    * @return List<Map<String,Object>>    返回类型
	    * @throws
	 */
	public List<Map<String, Object>> getUnreadCodeKey(Long managerAlertId) {
		return managerAlertDao.getUnreadCodeKey(managerAlertId);
	}
	/**
	 * 
	    * @Title: getAllCodeKeyByManagerId
	    * @Description: TODO(根据managerId获得 其 所有的key -用于类型列表展示)
	    * @param @param id
	    * @param @return    参数
	    * @return List<Map<String,Object>>    返回类型
	    * @throws
	 */
	public List<Map<String, Object>> getAllCodeKeyByManagerId(Long managerId) {
		return managerAlertDao.getAllCodeKeyByManagerId(managerId);
	}
	/**
	 * 
	    * @Title: saveOneManagerAlert
	    * @Description: (保存一条记录)
	    * @param @param managerAlert    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void saveOneManagerAlert(ManagerAlert managerAlert,Long managerId) {
		managerAlertDao.saveOneManagerAlert(managerAlert);
		updateRedisManagerAlertCount(managerId);
	}
	/**
	 * 
	    * @Title: checkExist
	    * @Description: (校验是否已经存在有效记录并返回)
	    * @param @param codeKey
	    * @param @param checkToken
	    * @param @return    参数
	    * @return ManagerAlert    返回类型
	    * @throws
	 */
	public ManagerAlert checkExist(String codeKey, String checkToken) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("codeKey", codeKey);
		map.put("checkToken", checkToken);
		ManagerAlert managerAlert=managerAlertDao.checkExist(map);
		return managerAlert;
	}
	/**
	 * 
	    * @Title: setInvalid
	    * @Description: (失效某条记录)
	    * @param @param managerAlertId
	    * @param @param invalidReason    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void setInvalid(Long managerAlertId,String invalidReason,Long managerId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("managerAlertId", managerAlertId);
		map.put("invalidReason", invalidReason);
		managerAlertDao.setInvalid(map);
		updateRedisManagerAlertCount(managerId);
	}
	/**
	 * 
	    * @Title: getList
	    * @Description: (获取列表)
	    * @param @param map
	    * @param @return    参数
	    * @return List<ManagerAlert>    返回类型
	    * @throws
	 */
	public List<ManagerAlert> getList(Map<String, Object> map) {
		return managerAlertDao.getList(map);
	}

	/**
	 * 
	    * @Title: updateRedisManagerAlertCount
	    * @Description: (设置，更新未读消息数量)
	    * @param @param managerId    参数
	    * @return void    返回类型
	    * @throws
	 */
	private void updateRedisManagerAlertCount(Long managerId){
		Integer countInteger =getCountUnread(managerId);
		redisService.setValue(redisService.REDIS_KEY_MANAGER_ALERT_COUNT+managerId, countInteger.toString(), 3L, TimeUnit.HOURS);
	}
	
}
