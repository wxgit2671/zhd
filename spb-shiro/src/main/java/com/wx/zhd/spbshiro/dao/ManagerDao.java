package com.wx.zhd.spbshiro.dao;

import com.wx.zhd.spbshiro.entity.Manager;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 后台管理用户数据访问接口
 *
 * @author Boya
 */
@Repository
public interface ManagerDao {
    Manager getById(Long id);

    List<Manager> getAll(Manager manager);

    List<Manager> getByName(String name);

    Manager findByLoginName(String loginName);

    int insert(Manager manager);

    int update(Manager manager);

    int updatePwd(Manager manager);

    int delete(Long id);

    List<Manager> getByRoleId(Long id);

    List<Manager> findAllLeader(@Param("id") Long id);

    /**
     * 查询催收人员，按照催收分组
     */
    List<Manager> findCollectionByGroup(@Param("groupId") Long groupId);

    /**
     * 查询催收人员，按照催收分组，正序
     */
    List<Manager> findCollectionByGroupAsc(@Param("groupId") Long groupId);

    /**
     * 查询催收人员，按照催收分组，反序
     */
    List<Manager> findCollectionByGroupDesc(@Param("groupId") Long groupId);

    /**
     * 查询催收人员，按照催收分组，正序，不带用户状态
     */
    List<Long> findCollectionByGroupS(@Param("groupId") Long groupId);

    List<Long> findAllId(@Param("roleIds") Long[] roleIds);

    int changeCollectionGroup(@Param("id") Long id, @Param("groupId") Long groupId);

    List<Manager> findAllBycondition(Map<String, Object> condition);

    List<Map<String, Object>> isAppointOut(Long managerId);

    List<Manager> getAvailableByRoleId(Long id);

    List<Long> findManagerIdsByCondition(Map<String, Object> condition);

    List<Manager> getAllOutSourceManager();

    Manager getByPhone(String phone);

    Manager getStateById(Manager manager);
}
