package com.wx.zhd.spbshiro.entity.sys;

import com.wx.zhd.spbshiro.entity.IdEntity;

import java.util.Date;

/**
 * 
    * @ClassName: ManagerAlert
    * @Description: (系统提示信息)
    * @date 2017年10月25日
    *
 */
@SuppressWarnings("serial")
public class ManagerAlert extends IdEntity {
	private Long managerId;
	private String codeKey;
	private Date alertTime;
	private String content;
	private Integer state;
	private String url;
	private Date gmtCreate;
	private Date gmtModified;
	private String checkToken;
	private String invalidReason;
	private Code code;
	
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	
	public String getCodeKey() {
		return codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	public Date getAlertTime() {
		return alertTime;
	}
	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public String getCheckToken() {
		return checkToken;
	}
	public void setCheckToken(String checkToken) {
		this.checkToken = checkToken;
	}
	
	public String getInvalidReason() {
		return invalidReason;
	}
	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}
	public Code getCode() {
		return code;
	}
	public void setCode(Code code) {
		this.code = code;
	}
}
