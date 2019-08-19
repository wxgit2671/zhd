package com.wx.zhd.spbshiro.dao.sys;

import com.wx.zhd.spbshiro.entity.sys.ManagerAlert;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ManagerAlertDao
 * @date 2017年10月25日
 */
@Repository
public interface ManagerAlertDao {

    Integer getCountUnread(Long managerId);

    Integer getCountUnreadNew(Map<String, Object> map);

    List<ManagerAlert> getUnreadList(Long managerId);

    void setStateToRead(Long managerAlertId);

    void setStateToIgnore(Long managerAlertId);

    List<ManagerAlert> getUnreadList(Map<String, Object> map);

    List<Map<String, Object>> getUnreadCodeKey(Long managerAlertId);

    void saveOneManagerAlert(ManagerAlert managerAlert);

    ManagerAlert checkExist(Map<String, Object> map);

    void setInvalid(Map<String, Object> map);

    List<ManagerAlert> getList(Map<String, Object> map);

    /**
     * @param @param map    参数
     * @return void    返回类型
     * @Title: disableValidRecordByKeyAndToken
     * @Description: (根据类型和token失效相关记录)
     */
    void disableValidRecordByKeyAndToken(Map<String, Object> map);

    /**
     * @param @param map    参数
     * @return void    返回类型
     * @Title: disableValidRecordByKeyAndToken
     * @Description: (根据类型和token及 managerId 失效相关记录)
     */
    void disableValidRecordByKeyAndTokenAndManagerId(Map<String, Object> map);

    List<Map<String, Object>> getAllCodeKeyByManagerId(Long managerId);


    ManagerAlert findRecentAnHourByUserId(String userId);
}
