package com.wx.zhd.spbshiro.config.shiro;

import com.wx.zhd.spbshiro.dao.ManagerDao;
import com.wx.zhd.spbshiro.dao.MenuDao;
import com.wx.zhd.spbshiro.entity.Manager;
import com.wx.zhd.spbshiro.entity.Menu;
import com.wx.zhd.spbshiro.utils.Digests;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.annotation.PostConstruct;

/**
 * 自定义认证、授权实现
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private ManagerDao managerDao;
	/**
	 * @Dscrription:菜单数据库查询类
	 * @author:	haidong
	 * @date: 2016年2月24日 上午9:30:48 
	 */
	@Autowired
	private MenuDao menuDao;

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 *//*
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Digests.HASH_ALGORITHM);
		matcher.setHashIterations(Digests.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}*/

	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Manager manager = managerDao.findByLoginName(token.getUsername());
		// 如果用户存在
		if (manager != null) {
			if("0".equals(manager.getState())){
				throw new LockedAccountException("账号被禁用！"); // 如果用户状态为禁用
			}
			String salt = manager.getLoginName();
			return new SimpleAuthenticationInfo(manager, manager.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else {
			throw new UnknownAccountException("用户名不存在！"); // 如果用户名错误
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Manager manager = (Manager) principals.getPrimaryPrincipal();
		if (manager != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.addRole(manager.getRole().getName());
			List<Menu> menus = menuDao.findByRoleId(manager.getRole().getId());
			for(Menu menu : menus){
				info.addStringPermission(menu.getMark());
			}
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}
