package com.wx.zhd.spbshiro.config.sitemesh;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class SitemeshConfig {
	@Bean
	public Filter sitemeshFilter(){
		return new SiteMeshFilter();
	}
}
