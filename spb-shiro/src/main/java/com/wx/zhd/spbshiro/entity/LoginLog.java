package com.wx.zhd.spbshiro.entity;

import java.util.Date;

/**
 * 登陆日志
 */
@SuppressWarnings("serial")
public class LoginLog extends IdEntity{
	private String ip;
	private Date loginTime;
	private String userName;
	private String name;
	/**
	 * 开始时间，查询条件，不保存
	 */
	private Date beginTime;
	/**
	 * 结束时间，查询条件，不保存
	 */
	private Date endTime;
	/**
	 * 登陆状态,1-登陆成功，2-登陆失败-非法ip
	 */
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
