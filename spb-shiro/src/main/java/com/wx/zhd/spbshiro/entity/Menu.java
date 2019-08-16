package com.wx.zhd.spbshiro.entity;

import java.util.List;

/**
 * @ClassName:Menu
 * @Dscrription:菜单实体类
 * @author:	haidong
 * @date: 2016年2月23日 上午9:51:30
 */
@SuppressWarnings("serial")
public class Menu extends IdEntity{
    private String name;
	private String path;
	private String mark;
	private String description;
	private String state;
	private String flag;
	private String level;
	private Long fatherId;
	private List<Menu> children;
	private boolean checked;
	private String isMenu;
	private String leftMenu;
	private Integer sort;
	private String target;
	public String getLeftMenu() {
        return leftMenu;
    }
    public void setLeftMenu(String leftMenu) {
        this.leftMenu = leftMenu;
    }
    public String getName() {
		return name;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getFatherId() {
		return fatherId;
	}
	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
	
}
