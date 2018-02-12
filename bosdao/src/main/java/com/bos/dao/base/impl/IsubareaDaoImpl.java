package com.bos.dao.base.impl;

import com.bos.dao.base.IsubareaDao;
import com.bos.domain.Subarea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IsubareaDaoImpl extends IbasedaoImpl<Subarea> implements IsubareaDao {

	@Override
	public List<Object[]> findbysubarea() {
/*SELECT
  r2.province,
  count(*)
FROM bc_subarea s LEFT JOIN bc_region r2 ON s.region_id = r2.id
GROUP BY r2.province;*/
//把sql转换成hql格式的
		String s = "select r.province,count(*) from Subarea s left join s.region r group by r.province";
		return (List<Object[]>) getHibernateTemplate().find(s);
	}
}
