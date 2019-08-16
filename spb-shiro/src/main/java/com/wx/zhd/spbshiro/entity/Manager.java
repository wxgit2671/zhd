package com.wx.zhd.spbshiro.entity;


import java.util.Date;

/**
 * 后台管理员实体类
 * 
 * @author Boya
 */
@SuppressWarnings("serial")
public class Manager extends IdEntity{
	private String loginName;
	private String password;
	private String name;
	private Long roleId;
	private Role role;
	private String state;
	private Manager leader;
	private Long leaderId;
	private Long creatId;
	private Date creatTime;
	private Date updateTime;
	private Manager creat;
    //是否需要ip白名单验证，0-否，1-是
	private String whiteList;
	private Long collectionGroupId;
	//负责的催收数量
	private Integer collectionCount;
	//登录后是否需要修改密码；0：需要；1：不需要  zhud 2017.10.31
	private Integer updatePassword;

	private String phone;
	//会员等级ID
	private Long memberDegreeId;


	public Integer getUpdatePassword() {
		return updatePassword;
	}
	public void setUpdatePassword(Integer updatePassword) {
		this.updatePassword = updatePassword;
	}
	public Date getCreatTime() {
        return creatTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
    public Manager getCreat() {
        return creat;
    }
    public void setCreat(Manager creat) {
        this.creat = creat;
    }
    public String getLoginName() {
		return loginName;
	}
	public Long getCreatId() {
        return creatId;
    }
    public void setCreatId(Long creatId) {
        this.creatId = creatId;
    }
    public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Manager getLeader() {
		return leader;
	}
	public void setLeader(Manager leader) {
		this.leader = leader;
	}
	public Long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	public String getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(String whiteList) {
		this.whiteList = whiteList;
	}

	public Long getCollectionGroupId() {
		return collectionGroupId;
	}

	public void setCollectionGroupId(Long collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
	}

	public Integer getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getMemberDegreeId() {
		return memberDegreeId;
	}

	public void setMemberDegreeId(Long memberDegreeId) {
		this.memberDegreeId = memberDegreeId;
	}

}
