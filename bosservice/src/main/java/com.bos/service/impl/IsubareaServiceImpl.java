package com.bos.service.impl;

import com.bos.dao.base.IsubareaDao;
import com.bos.domain.Subarea;
import com.bos.service.IsubareaService;
import com.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IsubareaServiceImpl implements IsubareaService {
	@Autowired
	private IsubareaDao isubareaDao;

	@Override
	public void save(Subarea moudele) {
		isubareaDao.save(moudele);
	}

	@Override
	public void pagequery(PageBean pageBean) {
		isubareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return isubareaDao.findall();
	}

	@Override
	public List<Subarea> findListNotassociatiion() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
//		添加过滤条件
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return isubareaDao.findbycriteria(detachedCriteria);
	}

	@Override
	public List<Subarea> findlistbydecidedid(String decidedzoneId) {
//		根据定区ID查询关联的分区
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
//		注意，decidedzone.id的字段查的是decidedzone_id这个字段，而这个字段本身就在subarea这张表里面，所以不需要join来查询，既然不需要join来查询，也就不需要起别名了，需要用别名的时候是需要多表查询的，而.的方式是对象导航的方式
		detachedCriteria.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		return isubareaDao.findbycriteria(detachedCriteria);
	}

	@Override
	public List<Object[]> findSubareasGroup() {
		return isubareaDao.findbysubarea();
	}
}
