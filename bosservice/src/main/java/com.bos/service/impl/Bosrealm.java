package com.bos.service.impl;

import com.bos.dao.base.IfunctionDao;
import com.bos.dao.base.IuserDao;
import com.bos.domain.Function;
import com.bos.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Bosrealm extends AuthorizingRealm {
	@Autowired
	private IuserDao userDao;

	//认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("自定义的realm中认证方法执行了。。。。");
		UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
		//获得页面输入的用户名
		String username = passwordToken.getUsername();
		//根据用户名查询数据库中的密码
		User user = userDao.findUserByUsername(username);
		if (user == null) {
			//页面输入的用户名不存在
			return null;
		}
		//简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		//框架负责比对数据库中的密码和页面输入的密码是否一致
		return info;
	}

	//授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//为用户授权
		info.addStringPermission("staff-list");
		//TODO 后期需要修改为根据当前登录用户查询数据库，获取实际对应的权限
		User user1 = (User) SecurityUtils.getSubject().getPrincipal();
//		User user2 = (User) principals.getPrimaryPrincipal();
//		System.out.println(user1 == user2);
//		获取当前用户对象
		//CTO 根据当前用户查询权限，admin需要进行特殊处理
		List<Function> findbycriteria = null;
		if (user1.getUsername().equals("admin")) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
//			这是超级管理员用户，用来查询权限数据
			findbycriteria = ifunctionDao.findbycriteria(detachedCriteria);
		} else {
			findbycriteria = ifunctionDao.findfunctionByuserId(user1.getId());
		}
		for (Function findbycriterion : findbycriteria) {
			info.addStringPermission(findbycriterion.getCode());
		}
		return info;
	}

	@Autowired
	private IfunctionDao ifunctionDao;
}
