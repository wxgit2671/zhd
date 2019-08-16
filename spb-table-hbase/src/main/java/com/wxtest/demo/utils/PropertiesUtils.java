package com.wxtest.demo.utils;

import com.wxtest.demo.common.constants.SysConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/** 
 * @ClassName: PropertiesUtils 
 * @Description: 配置文件工具类
 * @author L-liang
 * @date 2015年9月28日 上午9:08:23 
 *  
 */
public class PropertiesUtils {
	private static Logger logger = Logger.getLogger(PropertiesUtils.class);
	private static Properties props = null;
	/**
	 * @Title: getProperties
	 * @Description: 读取配置文件
	 * @return Properties 返回类型
	 * @throws
	 */
	public static Properties getProperties() {
		if (null == props) {
			props = readProperties(SysConstants.KEY + ".properties");
		}
		return props;
	}
	/** 
	 * @Title: getProperties 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param path
	 * @return    设定文件 
	 * @return Properties    返回类型 
	 * @throws 
	 */
	public static Properties getProperties(String path) {
		if (null == props) {
			props = readProperties(path);
		}
		return props;
	}
	private static Properties readProperties(String path){
		Properties props=new Properties();
		InputStream in = null;
		try {
			in = FileUtils.class.getClassLoader()
					.getResourceAsStream(path);
			props.load(in);
		} catch (IOException e) {
			logger.error("读取系统配置文件失败！",e);
		}finally{
			if(in != null){
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					logger.error("输入流关闭错误",e);
				}
			}
		}
		return props;
	}
	public static<T> T getBeanFromProperties(String path,Class<T> type){
		T t=null;
		try{
			t=type.newInstance();
			Properties properties=readProperties(path);
			Set<Object> keys= properties.keySet();
			for(Object key:keys){
				BeanUtils.setProperty(t, key.toString(), properties.get(key));
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return t;
	}
}
