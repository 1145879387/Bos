package com.bos.dao.base.impl;

import com.bos.dao.base.IuserDao;
import com.bos.domain.User;
import com.bos.utils.MD5Util;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Userdaoimpl extends IbasedaoImpl<User> implements IuserDao {
	@Override
	public User finduserbyusernameandpassword(String username, String generate) {
//		由于是加了盐的md5所以要先从数据库进行查询
		List<User> list = (List<User>) getHibernateTemplate().find("from User u where u.username=?", username);
		System.out.println(list.get(0));
		if (list != null && list.size() > 0) {
//			这是从数据库查询出来的密码
			String password = list.get(0).getPassword();
			if (MD5Util.verify(generate, password)) {
				return list.get(0);
			}
		}
//		List<User> list = (List<User>) getHibernateTemplate().find("from User  u where u.username = ? and u.password=?", username, generate);
		return null;
	}

	public User findUserByUsername(String username) {
		String hql = "FROM User u WHERE u.username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
