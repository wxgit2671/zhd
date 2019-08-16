package com.wxtest.demo.common.constants;
/**
 * This file created by wangxin on 2019/5/6.
 */

import java.util.ResourceBundle;

/**
 * @author wangxin
 * @classDescription 系统常量获取
 * @create 2019-05-06 14:11
 **/
public interface SysConstants {

     String PROJECT_NAME = ResourceBundle.getBundle("application").getString("project_name");
    /**
     * 资源文件.
     */
     String KEY = "application-" + PROJECT_NAME;
}
