package com.wxtest.demo.entity;


/**
 * 通话详单
 *
 * @author liang
 *
 */
public class SolrCallDetails {

	private String id;

	private Long userId;

	private String phone;

	private String otherPhone;

	private Integer callType;

	private String startTime;

	private Long duration;

	private Integer fee;

	private String commType;

	private String callLocation;
	/**
	 * 电话类型
	 */
	private String type;
	/**
	 * 敏感电话名称
	 */
	private String platform;

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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Long getDuration() {
		return duration;
	}


	public void setDuration(Long duration) {
//		if(duration instanceof Long){
//			this.duration=(Long) duration;
//		}else if(duration instanceof Integer){
//			this.duration=Long.valueOf(duration+"");
//		}
		this.duration=duration;
	}

	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public String getCommType() {
		return commType;
	}
	public void setCommType(String commType) {
		this.commType = commType;
	}
	public String getCallLocation() {
		return callLocation;
	}
	public void setCallLocation(String callLocation) {
		this.callLocation = callLocation;
	}
	public Integer getCallType() {
		return callType;
	}
	public void setCallType(Integer callType) {
		this.callType = callType;
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

	@Override
	public boolean equals(Object obj){
		if (this == obj){
			return  true;
		}
		if (!(obj instanceof SolrCallDetails)) {
			return  false;
		}
		SolrCallDetails other=(SolrCallDetails)obj;
		if (this.getUserId().equals(other.getUserId())
				&& this.getOtherPhone().equals(other.getOtherPhone())
				&& this.getStartTime().equals(other.getStartTime())){
			return true;
		}
		return false;
	}
}
