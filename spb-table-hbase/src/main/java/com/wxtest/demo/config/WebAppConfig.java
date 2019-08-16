package com.wxtest.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    /**
     * 配置拦截器
     *
     * @author wangxin
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserTokenInterceptor()).addPathPatterns("/**");
    }
}
