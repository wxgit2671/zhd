package com.wx.zhd.spbshiro.controller.sys;

import com.github.pagehelper.PageHelper;
import com.wx.zhd.spbshiro.controller.BaseController;
import com.wx.zhd.spbshiro.entity.Manager;
import com.wx.zhd.spbshiro.entity.sys.ManagerAlert;
import com.wx.zhd.spbshiro.service.sys.ManagerAlertService;
import com.wx.zhd.spbshiro.service.sys.RedisService;
import com.wx.zhd.spbshiro.tp.Result;
import com.wx.zhd.spbshiro.utils.persistence.Page;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 
    * @ClassName: ManagerAlertController
    * @Description: (系统消息提示)
    * @date 2017年10月25日
    *
 */
@Controller
@RequestMapping("/sys/header")
public class ManagerAlertController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(ManagerAlertController.class);
    @Autowired
    private ManagerAlertService managerAlertService;
    @Autowired
    private RedisService redisService;
    /**
     * 
        * @Title: Result
        * @Description: (系统消息提示数量)
        * @param @param model
        * @param @return    参数
        * @return Result    返回类型
        * @throws
     */
    @ResponseBody
    @RequestMapping("/alert")
    public Result countAlert (Model model) {
        Manager currentManager = currentUser();
        //reids 查询缓存消息数量
        String count=redisService.get(redisService.REDIS_KEY_MANAGER_ALERT_COUNT+currentManager.getId());
        if (StringUtils.isEmpty(count)) {
        	 Integer countInteger= managerAlertService.getCountUnread(currentManager.getId());
        	 redisService.setValue(redisService.REDIS_KEY_MANAGER_ALERT_COUNT+currentManager.getId(), countInteger.toString(), 3L, TimeUnit.HOURS);
//        	 redisService.setValue(redisService.REDIS_KEY_MANAGER_ALERT_COUNT+currentManager.getId(), "999", 3L, TimeUnit.HOURS);
        	 count=countInteger.toString();
		}
        
        return success("count", count);
    }
    /**
     * 
        * @Title: list
        * @Description: (未读消息列表)
        * @param @param model
        * @param @return    参数
        * @return String    返回类型
        * @throws
     */
    @RequestMapping("/list")
    public String list(Model model, Page<ManagerAlert> page,String codeKey,Integer state) {
    	if (state==null) {
			state=0;
		}
    	Manager currentManager = currentUser();
//    	List<Map<String, Object>> codeKeys=managerAlertService.getUnreadCodeKey(currentManager.getId());
    	List<Map<String, Object>> codeKeys=managerAlertService.getAllCodeKeyByManagerId(currentManager.getId());
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("state", state);
    	map.put("codeKey", codeKey);
    	map.put("managerId", currentManager.getId());
    	PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<ManagerAlert> list=managerAlertService.getList(map);
		page.setResult(list);
		model.addAttribute("page", page);
		model.addAttribute("codeKeys", codeKeys);
		model.addAttribute("codeKey", codeKey);
		model.addAttribute("state", state);
		return "alert/alert-list";
	}
    /**
     * 
        * @Title: readMessage
        * @Description: (已读消息)
        * @param @return    参数
        * @return String    返回类型
        * @throws
     */
    @RequestMapping("/readMessage")
    @ResponseBody
    public Result readMessage(Model model,Long managerAlertId) {
    	managerAlertService.setStateToRead(managerAlertId,currentUser().getId());
    	return null;
	}
    /**
     * 
        * @Title: readMessage
        * @Description: (忽略消息)
        * @param @return    参数
        * @return String    返回类型
        * @throws
     */
    @RequestMapping("/ignoreMessage")
    @ResponseBody
    public Result ignoreMessage(Model model,Long managerAlertId) {
    	managerAlertService.setStateToIgnore(managerAlertId,currentUser().getId());
    	return null;
	}
}
