package com.wx.zhd.spbshiro.entity.sys;


import com.wx.zhd.spbshiro.entity.IdEntity;

/**
 * @ClassName: Code
 * @Description: 编码表
 */
public class Code extends IdEntity {
    /**
     * 编码
     */
    private String key;
    /**
     * 内容
     */
    private String value;
    /**
     * 分类
     */
    private String category;
    /**
     * 排序字段
     */
    private Integer sort;
    /**
     * 状态： 状态 1正常 0禁用
     */
    private String state;
    /**
     * 关键字：用于条件搜索
     */
    private String keyWord;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


}
