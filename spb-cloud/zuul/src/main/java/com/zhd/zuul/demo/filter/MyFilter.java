package com.zhd.zuul.demo.filter;
/**
 * This file created by wangxin on 2019/9/11.
 */

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxin
 * @classDescription 路由过滤器
 * @create 2019-09-11 14:37
 **/
@Component
public class MyFilter extends ZuulFilter {
private Logger logger= LoggerFactory.getLogger(MyFilter.class);
    /**
     * 配置过滤类型，有四种不同生命周期的过滤器类型
     * 1. pre：路由之前
     * 2. routing：路由之时
     * 3. post：路由之后
     * 4. error：发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request=context.getRequest();
        HttpServletResponse response = context.getResponse();
        logger.info("当前访问路径"+request.getRequestURI());
        String user = request.getParameter("user");
        if (user == null) {
            logger.warn("user is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("user is empty");
            } catch (IOException e) {
                logger.error("当前响应io异常：{}",e.getMessage(),e);
            }
        } else {
            logger.info("OK");
        }
        return null;
    }
}
