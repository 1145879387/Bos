package com.bos.service.impl;

import com.bos.dao.base.IstaffDao;
import com.bos.domain.Staff;
import com.bos.service.IstaffService;
import com.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IstaffServiceImpl implements IstaffService {
	@Override
	public void save(Staff moudele) {
		istaffDao.save(moudele);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		istaffDao.pageQuery(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
//		这其实是一个逻辑删除，需要先把数据处理一下
		if (StringUtils.isNotBlank(ids)) {
			String[] split = ids.split(",");
			for (String s : split) {
				istaffDao.executeupdate("staff.delete", s);
			}
		}
	}

	@Override
	public Staff findbyid(String id) {
//		根据Id查询取派员
		return istaffDao.findByid(id);
	}

	@Override
	public void update(Staff staff) {
//		根据id修改取派员
		istaffDao.update(staff);
	}

	@Override
	public List<Staff> findlistnotdelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
//		添加过滤条件，detalg=0是未删除的
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
//		detachedCriteria.add(Restrictions.ne("deltag", 1));
		return istaffDao.findbycriteria(detachedCriteria);
	}

	@Autowired
	private IstaffDao istaffDao;
}
