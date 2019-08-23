package com.wx.zhd.spbshiro.entity;

import java.io.Serializable;

/**
 * 通用实体基类
 */
public class IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
