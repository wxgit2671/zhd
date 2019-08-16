package com.wxtest.demo.dao;

import com.wxtest.demo.entity.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户数据访问接口
 * 
 * @author Boya
 *
 */
@Repository
public interface UserDao {
    
    /**
     * @return
     * GY
     * 2017年8月30日
     * 导数
     */
    List<HashMap<String,String>> getMonth8Upc(@Param("start") Date start, @Param("end") Date end);

	public User getById(Long id);

	List<User> getAll();

	User findByPhone(String phone);

	int insert(User user);

	int update(User user);

	int delete(Long id);

	User getByPhone(String phone);
	List<User> findByAuditState(String auditState);

	Integer countNewApply();
	Long findUserIdByIdCardNum(@Param("idcardNum") String idcardNum, @Param("auditState") String auditState);

	List<Long>  queryUser();

	/**
	 * 查询所有需要审核的用户
	 * @return
	 * @author:	haidong
	 * @date: 2016年2月16日 下午5:37:24
	 */
	public List<String> findWaitCheck();

	/**
	 * 获取一个需要审核的用户
	 * @return
	 * @author:	haidong
	 * @date: 2016年2月16日 下午5:37:24
	 */
	public Long findOneWaitCheck(@Param("notInIds") List<String> notInIds, @Param("idCardNum") String idCardNum);

	User findByShareCode(String shareCode);

	/**
	 * 查询真信已回调但状态仍未更新的用户
	 * @return 用户ID和真信认证结果
	 */
	List<Map> getAuditStateUnchangedUser();


	void updateMemberDegreeTo2(List<Long> userIdsList);

	/**
	 * 清空delayDegree相关字段
	 * @param id
	 * @return
	 */
	int clearDelayDegree(@Param("id") Long id);

	/**
	 * 查询delayDegreeDate为传入日期的记录
	 * @param delayDegreeDate
	 * @return
	 */
	List<User> getWaitForUpdateDegree(@Param("delayDegreeDate") String delayDegreeDate);

	List<Map<String, Object>> getByIds(List<Map<String, Object>> dataId);

	List<Map<String, Object>> getNotInUsersByUserId(List<Map<String, Object>> dataId);
}
