package com.bos.service.impl;

import com.bos.dao.base.IuserDao;
import com.bos.domain.Role;
import com.bos.domain.User;
import com.bos.service.Iuserservice;
import com.bos.utils.MD5Util;
import com.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class IuserserviceImpl implements Iuserservice {
	@Autowired
	private IuserDao iuserDao;

	@Override
	public User login(User moudele) {
//		注解不要忘记，直接掉DAO
		return iuserDao.finduserbyusernameandpassword(moudele.getUsername(), moudele.getPassword());
	}

	@Override
	public void editPassword(String id, String password) {
//		根据用户id来修改密码
		String generate = MD5Util.generate(password);
		iuserDao.executeupdate("user.editpassword", generate, id);
	}

	@Override
	public void save(User moudele, String[] rowIds) {
//		添加一个用户，同时关联角色
		String generate = MD5Util.generate(moudele.getPassword());
		moudele.setPassword(generate);
		iuserDao.save(moudele);
		if (rowIds != null && rowIds.length > 0) {
			for (String rowId : rowIds) {
//				手动构造一个托管对象，让用户去关联角色对象
				Role role = new Role(rowId);
//				注意，这里必须存储的是对象形式是role!还需要看USER表的inverse属性
				moudele.getRoles().add(role);
			}
		}
		/*
		* Hibernate:
    insert
    into
        t_user
        (username, password, salary, birthday, gender, station, telephone, remark, id)
    values
        (?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate:
    insert
    into
        user_role
        (user_id, role_id)
    values
        (?, ?)
*/
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		iuserDao.pageQuery(pageBean);
	}
}
