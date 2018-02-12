package com.bos.service.impl;

import com.bos.dao.base.IfunctionDao;
import com.bos.domain.Function;
import com.bos.domain.User;
import com.bos.service.Ifunctionservice;
import com.bos.utils.Bosutils;
import com.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IfunctionserviceImpl implements Ifunctionservice {
	@Override
	public List<Function> findall() {
		return ifunctionDao.findall();
	}

	//添加权限
	@Override
	public void save(Function moudele) {
		ifunctionDao.save(moudele);
	}

	@Override
	public void pagequery(PageBean pageBean) {
		ifunctionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findmenu() {
//		查询当前用户
		User getlogin = Bosutils.getlogin();
		List<Function> list = null;
		if (getlogin.getUsername().equals("admin")) {
//			如果是超级管理员就查询所有菜单
			list = ifunctionDao.findallmenu();
		} else {
			list = ifunctionDao.findMenubyUserid(getlogin.getId());
		}
		return list;
	}

	@Autowired
	private IfunctionDao ifunctionDao;
}
