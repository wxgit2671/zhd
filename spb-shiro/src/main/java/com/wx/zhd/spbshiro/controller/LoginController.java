package com.wx.zhd.spbshiro.controller;

import com.wx.zhd.spbshiro.entity.LoginLog;
import com.wx.zhd.spbshiro.entity.Manager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录控制类
 *
 * @author wx
 */
@Controller
public class LoginController extends BaseController {
    /**
     * @Dscrription:登陆日志
     * @author: wx
     */
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping({"/", "/index"})
    public String index() {
	 /* //读取 menu 信息
        List<Menu> menus=menuService.getAllMenu();
        TreeTableUtils treeTableUtils =new TreeTableUtils(menus);
//        menus=treeTableUtils.getFirstLevelTree();
        menus=treeTableUtils.buildTree();
        model.addAttribute("menus", menus);
        logger.warn("menus size is :"+menus.size());
        System.out.println("log for menus size:"+menus.size());*/
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 登录接口
     *
     * @param code               验证码
     * @param manager            用户信息
     * @param redirectAttributes 重定向
     * @param request            请求信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginInput(String code, Manager manager, RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        try {
            boolean flag = validateCode(code, request.getSession());
            if (flag) {
                //shiro认证
                SecurityUtils.getSubject().login(new UsernamePasswordToken(manager.getLoginName(),
                        manager.getPassword()));
                //设置登录session过期时间
                SecurityUtils.getSubject().getSession().setTimeout(1000 * 60 * 5);
                //记录日志
                Manager currentUser = currentUser();
                LoginLog loginLog = new LoginLog();
                loginLog.setLoginTime(new Date());
                //loginLog.setIp(CusAccessUtil.getIpAddress(request));
                loginLog.setName(currentUser.getName());
                loginLog.setUserName(currentUser.getLoginName());
                return "redirect:/";
            } else {
                addFailMessage(redirectAttributes, "验证码不正确！");
            }
        } catch (UnknownAccountException e) {
            addFailMessage(redirectAttributes, e.getMessage());
            logger.error("用户名密码不正确manager--" + manager.getLoginName() + "-Password()-" + manager.getPassword(), e);
        } catch (LockedAccountException e) {
            addFailMessage(redirectAttributes, e.getMessage());
            logger.error("用户名密码不正确manager--" + manager.getLoginName() + "-Password()-" + manager.getPassword(), e);
        } catch (AuthenticationException e) {
            addFailMessage(redirectAttributes, "用户名密码不正确！");
            logger.error("用户名密码不正确manager--" + manager.getLoginName() + "-Password()-" + manager.getPassword(), e);
        } catch (Exception e) {
            logger.error("用户名密码不正确manager--" + manager.getLoginName() + "-Password()-" + manager.getPassword(), e);
            addFailMessage(redirectAttributes, e.getMessage());
        }
        return "redirect:/login";
    }

    @RequestMapping("/checkCode")
    @ResponseBody
    public Map<String, Object> checkCode(String code, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        boolean flag = validateCode(code, session);
        if (flag) {
            addSuccessMessage(result, "ok");
        } else {
            addFailMessage(result, "验证码不正确");
        }
        return result;
    }

    private boolean validateCode(String code, HttpSession session) {
        Object codeObj = session.getAttribute("code");
        if (codeObj != null) {
            String codeOnSession = codeObj.toString();
            if (codeOnSession.equalsIgnoreCase(code)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 用户禁止登录页面
     * @return
     */
    @RequestMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }
}
