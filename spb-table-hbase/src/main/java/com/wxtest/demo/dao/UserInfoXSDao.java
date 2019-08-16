package com.wxtest.demo.dao;

import com.wxtest.demo.entity.UserInfoXS;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据访问接口
 * 
 * @author Boya
 *
 */
@Repository
public interface UserInfoXSDao {
	 UserInfoXS getById(Long id);

	List<UserInfoXS> getAll();

	UserInfoXS findByPhoneAndUserId(@Param("phone") String phone, @Param("userId") Long userId);

	int insert(UserInfoXS userInfoXS);

	int update(UserInfoXS userInfoXS);

	
}
