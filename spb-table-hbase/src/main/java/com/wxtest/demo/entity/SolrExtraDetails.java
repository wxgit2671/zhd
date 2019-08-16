package com.wxtest.demo.entity;

import java.util.Date;

/**
 * 敏感通话详单
 * SolrExtraDetails
 * @author liang
 *
 */
public class SolrExtraDetails {

	private String id;

	private Long userId;

	private String phone;

	private String otherPhone;
	/**
	 * 第一次通话时间
	 */

	private String firstCall;
	/**
	 * 最后一次通话时间
	 */

	private String lastCall;
	/**
	 * 接听断电话次数
	 */

	private Integer inTimes;
	/**
	 * 拨打电话次数
	 */

	private Integer outTimes;
	/**
	 *接听断电话时长
	 */

	private Long inDuration;
	/**
	 *拨打电话时长
	 */

	private Long outDuration;
	/**
	 * 接听电话费用
	 */

	private Integer inFee;
	/**
	 * 拨打电话费用
	 */

	private Integer outFee;
	/**
	 * 通话次数
	 */

	private Integer allTimes;
	/**
	 * 最后一次通话地址
	 */

	private String callLocation;
	/**
	 * 电话类型
	 */

	private String type;
	/**
	 * 敏感电话名称
	 */

	private String platform;
	/**
	 * 通话类型
	 */

	private String commType;


	/**
	 * 用来判断时间的标志位
	 */
	private Date firstCallTime1;

	private Date LastCallTime1;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}



	public void setFirstCall(String firstCall) {
		this.firstCall = firstCall;
	}

	public String getLastCall() {
		return lastCall;
	}

	public void setLastCall(String lastCall) {
		this.lastCall = lastCall;
	}

	public Integer getInTimes() {
		return inTimes;
	}

	public void setInTimes(Integer inTimes) {
		this.inTimes = inTimes;
	}

	public Integer getOutTimes() {
		return outTimes;
	}

	public void setOutTimes(Integer outTimes) {
		this.outTimes = outTimes;
	}

	public Long getInDuration() {
		return inDuration;
	}

	public void setInDuration(Long inDuration) {
		this.inDuration = inDuration;
	}

	public Long getOutDuration() {
		return outDuration;
	}

	public void setOutDuration(Long outDuration) {
		this.outDuration = outDuration;
	}

	public Integer getInFee() {
		return inFee;
	}

	public void setInFee(Integer inFee) {
		this.inFee = inFee;
	}

	public Integer getOutFee() {
		return outFee;
	}

	public void setOutFee(Integer outFee) {
		this.outFee = outFee;
	}

	public Integer getAllTimes() {
		return allTimes;
	}

	public void setAllTimes(Integer allTimes) {
		this.allTimes = allTimes;
	}

	public String getCallLocation() {
		return callLocation;
	}

	public void setCallLocation(String callLocation) {
		this.callLocation = callLocation;
	}

	public String getFirstCall() {
		return firstCall;
	}

	public Date getFirstCallTime1() {
		return firstCallTime1;
	}

	public void setFirstCallTime1(Date firstCallTime1) {
		this.firstCallTime1 = firstCallTime1;
	}

	public Date getLastCallTime1() {
		return LastCallTime1;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setLastCallTime1(Date lastCallTime1) {

		LastCallTime1 = lastCallTime1;
	}

	public String getCommType() {
		return commType;
	}

	public void setCommType(String commType) {
		this.commType = commType;
	}
}
