package com.zhd.zuul.demo.config;
/**
 * This file created by wangxin on 2019/9/11.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxin
 * @classDescription 自定义拦截器
 * @create 2019-09-11 16:01
 **/
@Component
public class MyInterceptor extends HandlerInterceptorAdapter {
    private Logger logger= LoggerFactory.getLogger(getClass());
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (errorCodeList.contains(response.getStatus())) {
            logger.error("所访问路径出错"+request.getRequestURI());
            response.sendRedirect("/error/" + response.getStatus()+".html");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
