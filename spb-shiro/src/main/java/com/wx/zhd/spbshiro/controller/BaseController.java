package com.wx.zhd.spbshiro.controller;

import com.wx.zhd.spbshiro.entity.Manager;
import com.wx.zhd.spbshiro.tp.Result;

import org.apache.shiro.SecurityUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BaseController {
    protected String getRootPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath() + "/";
    }
    
	/**
	 * 封装成功时返回的对象（返回json数据时使用）
	 */
	protected Result success(String msg, Object data){
		return new Result("1", msg, data);
	}
	
	/**
	 * 封装失败时返回的对象（返回json数据时使用）
	 */
	protected Result error(String msg, Object data){
		return new Result("0", msg, data);
	}

	/**
	 * 获取当前登陆用户
	 * @return
	 * @author:	haidong
	 * @date: 2016年1月16日 下午2:34:42 
	 */
	protected Manager currentUser() {
		return (Manager) SecurityUtils.getSubject().getPrincipal();
	}

    public void addSuccessMessage(Map<String,Object> result,String msg){
    	result.put("msg", msg);
    	result.put("code", 1);
    }
    public void addFailMessage(Map<String,Object> result,String msg){
    	result.put("msg", msg);
    	result.put("code", 0);
    }

	protected void addSuccessMessage(Model model, String msg){
		addMessage(model, msg, "1");
	}

	protected void addFailMessage(Model model, String msg){
		addMessage(model, msg, "0");
	}

	private void addMessage(Model model, String msg, String code){
		if (model instanceof RedirectAttributes) {
			((RedirectAttributes) model).addFlashAttribute("msg", msg);
			((RedirectAttributes) model).addFlashAttribute("code", code);
		} else {
			model.addAttribute("msg", msg);
			model.addAttribute("code", code);
		}
	}

}
