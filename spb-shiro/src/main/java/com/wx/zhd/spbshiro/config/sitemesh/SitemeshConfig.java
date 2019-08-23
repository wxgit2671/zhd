package com.wx.zhd.spbshiro.config.sitemesh;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
/**
 * @author wangxin
 * @classDescription 使用SiteMeshFilter过滤器将显示页面进行组合
 * @create 2019-08-16 11:13
 **/
@Configuration
public class SitemeshConfig {
	@Bean
	public Filter sitemeshFilter(){
		return new SiteMeshFilter();
	}
}
