package com.wxtest.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 验证拦截器
 *
 * @author wangxin
 */
public class UserTokenInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(UserTokenInterceptor.class);

    /**
     * 不需要验证token的url
     */
    @SuppressWarnings("serial")
    public static Map<String, Boolean> excludeActions = new HashMap<String, Boolean>() {
        {
            this.put("/sjmh/report/opd/getReport", true);
            this.put("/test/hello", true);
            this.put("/test/user", true);
            this.put("/test/user/insert", true);
        }
    };


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
    }


    /**
     * @param @param  request
     * @param @return 设定文件
     * @return boolean    true 通过验证，false 没有通过验证
     * @Title: isPass
     * @Description: 判断是否通过验证
     */
    private boolean isPass(HttpServletRequest request) {
        String action = request.getRequestURI().substring(request.getContextPath().length());
        String userId = request.getParameter("userId");
		/*if(userId != null) {
			saveAppVisitTime(Long.parseLong(userId));
		}*/
        if (action.equals("/error")) {
            return true;
        }
        if (excludeActions.get(action) != null && excludeActions.get(action) == true) {
            return true;
        }
        logger.info("请求url:{}需要验证token", action);
        return checkToken(request);
    }

    /**
     * @param @param  request
     * @param @return 设定文件
     * @return boolean    true 验证通过 false 验证失败
     * @Title: checkToken
     * @Description: 验证token
     */
    private boolean checkToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        String userId = request.getParameter("userId");
        //没有传入token 验证失败
        if (StringUtils.isBlank(token) || StringUtils.isBlank(userId)) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return isPass(request);

    }

}
