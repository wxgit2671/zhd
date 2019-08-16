package com.wxtest.demo.config.database;
/**
 * This file created by wangxin on 2019/4/18.
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口文件注释(Interface file)
 *
 * @author wangxin
 * @interfaceDescription 作用于类、接口或者方法上
 * @create 2019-04-18 18:40
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
