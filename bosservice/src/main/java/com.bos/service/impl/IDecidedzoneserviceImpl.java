package com.bos.service.impl;

import com.bos.dao.base.IdecidedzoneDao;
import com.bos.dao.base.IsubareaDao;
import com.bos.domain.Decidedzone;
import com.bos.domain.Subarea;
import com.bos.service.IDecidedzoneservice;
import com.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IDecidedzoneserviceImpl implements IDecidedzoneservice {
	@Override
	public void save(Decidedzone moudele, String[] subareaid) {
//		事物注解写到接口上和实现方法上都是正确的
//      这里需要添加定区，并同时关联分区
		idecidedzoneDao.save(moudele);
//		注意，如果Decidedzone的hbm的inverse属性为true的话，则说明1的一段放弃了维护，如果一的一段放弃了维护，则不能用moudele.getSubareas().add()方法来维护关系，如果一定要用这种方法，则需要把true改为flase
		for (String s : subareaid) {
//			如果是要更新的话是不可以new对象的，因为new出来的对象是一个托管对象，而find查找是一个持久化类型的对象，持久化类型的对象才可以进行事物的提交，而且new的对象逻辑也是有问题的
			Subarea byid = isubareaDao.findByid(s);
//			让多的一方来关联1的一方
			byid.setDecidedzone(moudele);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		idecidedzoneDao.pageQuery(pageBean);
	}

	@Autowired
	private IdecidedzoneDao idecidedzoneDao;
	@Autowired
	private IsubareaDao isubareaDao;
}
