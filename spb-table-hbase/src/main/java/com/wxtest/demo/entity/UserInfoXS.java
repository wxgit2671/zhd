package com.wxtest.demo.entity;


import java.util.Date;

/**
 * 用户信息类
 * @author lizhichao
 *
 */

@SuppressWarnings("serial")
public class UserInfoXS {
	private Integer id;
	private Long userId;
	private String phone;
	private String name;
	private String address;
	private String identity;
	private String openDate;
	private String feeUpdateDate;

	private Date updateTime;
	private Date createTime;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getFeeUpdateDate() {
		return feeUpdateDate;
	}

	public void setFeeUpdateDate(String feeUpdateDate) {
		this.feeUpdateDate = feeUpdateDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
