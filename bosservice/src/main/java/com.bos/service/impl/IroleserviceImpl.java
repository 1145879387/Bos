package com.bos.service.impl;

import com.bos.dao.base.IroleDao;
import com.bos.domain.Function;
import com.bos.domain.Role;
import com.bos.service.Iroleservice;
import com.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IroleserviceImpl implements Iroleservice {
	@Override
	public void save(Role moudele, String functionids) {
		iroleDao.save(moudele);
		if (StringUtils.isNotBlank(functionids)) {
			String[] split = functionids.split(",");
			for (String s : split) {
//				让角色去关联权限
//				该对象现在是托管状态的对象
//				Function function = new Function(functionids);
				Function function = new Function(s);
				moudele.getFunctions().add(function);
			}
		}
	}

	@Override
	public void pagequery(PageBean pageBean) {
		iroleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findall() {
		return iroleDao.findall();
	}

	@Autowired
	private IroleDao iroleDao;
}
