package com.wxtest.demo.entity;


import java.util.Date;

import lombok.Data;

/**
 * 用户实体类
 * 
 * @author Boya
 *
 */
@SuppressWarnings("serial")
public class User {
	private Integer id;
	private String phone;
	private String password;
	private String name;
	private String state;
	private String infoState;
	private Date registTime;
	private Long creditRatingId;
	private String channelId;
	private Long deviceType;
	private Integer rejectTimes;
	private String auditState;
	/**
	 * 0-正常，1-促销用户，2-已促销用户
	 */
	private String marketState;
	/**
	 * 用户认证状态
	 */
	private String jobState;
	private String emergencyContactState;
	private String taobaoState;
	private String zhimaState;
	private String telecomState;
	private String identityState;
	private String haixiangAuthState;

	private String shareCode;
	private Long inviteUserId;
	public Long getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(Long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    /**
	 * 会员等级管理表关联ID
	 * lisong 2017-12-08 11:05
	 */
	private Long memberDegreeId;
	private String sex;
	private String systemType;
	/**
	 *  银行卡绑定状态
	 */
	private String isBindBankCard;

	public String getIsBindBankCard() {
		return isBindBankCard;
	}

	public void setIsBindBankCard(String isBindBankCard) {
		this.isBindBankCard = isBindBankCard;
	}

	/**
	 * 会员卡权益保留日期
	 */
	private Date delayDegreeDate;
	/**
	 * 会员卡权益保留等级
	 */
	private Long delayDegreeId;

	public String getHaixiangAuthState() {
		return haixiangAuthState;
	}

	public void setHaixiangAuthState(String haixiangAuthState) {
		this.haixiangAuthState = haixiangAuthState;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Long deviceType) {
		this.deviceType = deviceType;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getInfoState() {
		return infoState;
	}

	public void setInfoState(String infoState) {
		this.infoState = infoState;
	}

	private String token;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Long getCreditRatingId() {
		return creditRatingId;
	}

	public void setCreditRatingId(Long creditRatingId) {
		this.creditRatingId = creditRatingId;
	}

	public Integer getRejectTimes() {
		return rejectTimes;
	}

	public void setRejectTimes(Integer rejectTimes) {
		this.rejectTimes = rejectTimes;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getMarketState() {
		return marketState;
	}

	public void setMarketState(String marketState) {
		this.marketState = marketState;
	}

	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	public String getEmergencyContactState() {
		return emergencyContactState;
	}

	public void setEmergencyContactState(String emergencyContactState) {
		this.emergencyContactState = emergencyContactState;
	}

	public String getTaobaoState() {
		return taobaoState;
	}

	public void setTaobaoState(String taobaoState) {
		this.taobaoState = taobaoState;
	}

	public String getZhimaState() {
		return zhimaState;
	}

	public void setZhimaState(String zhimaState) {
		this.zhimaState = zhimaState;
	}

	public String getTelecomState() {
		return telecomState;
	}

	public void setTelecomState(String telecomState) {
		this.telecomState = telecomState;
	}

	public String getIdentityState() {
		return identityState;
	}

	public void setIdentityState(String identityState) {
		this.identityState = identityState;
	}
	
	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public Long getMemberDegreeId() {
		return memberDegreeId;
	}

	public void setMemberDegreeId(Long memberDegreeId) {
		this.memberDegreeId = memberDegreeId;
	}

	public Date getDelayDegreeDate() {
		return delayDegreeDate;
	}

	public void setDelayDegreeDate(Date delayDegreeDate) {
		this.delayDegreeDate = delayDegreeDate;
	}

	public Long getDelayDegreeId() {
		return delayDegreeId;
	}

	public void setDelayDegreeId(Long delayDegreeId) {
		this.delayDegreeId = delayDegreeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
