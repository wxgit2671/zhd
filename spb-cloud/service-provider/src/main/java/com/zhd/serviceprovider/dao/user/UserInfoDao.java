package com.zhd.serviceprovider.dao.user;

import com.zhd.serviceprovider.entity.user.UserInfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("rawtypes")
public interface UserInfoDao {

	 UserInfo getByUserId(@Param("userId") Long userId);

}
