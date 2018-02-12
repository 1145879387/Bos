package com.bos.dao.base.impl;

import com.bos.dao.base.IregionnDao;
import com.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IregionnDaoImpl extends IbasedaoImpl<Region> implements IregionnDao {

	@Override
	public List<Region> findlistbyq(String q) {
//		这是区域表，根据q参数来进行模糊查询
		List<Region> objects = (List<Region>) getHibernateTemplate().find("from Region r where r.shortcode like ? or r.citycode like ?", "%" + q + "%", "%" + q + "%");
		return objects;
	}
}
