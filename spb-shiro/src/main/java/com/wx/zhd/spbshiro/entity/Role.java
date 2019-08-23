package com.wx.zhd.spbshiro.entity;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 后台角色实体类
 */
@SuppressWarnings("serial")
public class Role extends IdEntity {
	/**
	 * @Dscrription:默认角色，不可删除
	 */
	public static final String TYPE_DEFAULT="99";
	/**
	 * @Dscrription:系统角色
	 */
	public static final String TYPE_SYS="0";
	/**
	 * @Dscrription:其他角色
	 */
	public static final String TYPE_OTHER="1";
	private String name;
	private String description;
	private BigDecimal amountLimit;
	private String type;
	private String advanced;
	/**
	 * 创建人
	 */
	private Long creatId;
	private Date creatTime;
	private Date updateTime;
	private Manager manager;
	
	public Manager getManager() {
        return manager;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getCreatTime() {
        return creatTime;
    }
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }
    public Long getCreatId() {
        return creatId;
    }
    public void setCreatId(Long creatId) {
        this.creatId = creatId;
    }
    public BigDecimal getAmountLimit() {
		return amountLimit;
	}
	public void setAmountLimit(BigDecimal amountLimit) {
		this.amountLimit = amountLimit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAdvanced() {
		return advanced;
	}
	public void setAdvanced(String advanced) {
		this.advanced = advanced;
	}
	
	
}
