package com.bos.dao.base.impl;

import com.bos.dao.base.IfunctionDao;
import com.bos.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IfunctionDaoImpl extends IbasedaoImpl<Function> implements IfunctionDao {
	@Override
	public List<Function> findall() {
		List<Function> list = (List<Function>) getHibernateTemplate().find("from Function f where f.parentFunction is null");
		return list;
	}

	@Override
	public List<Function> findfunctionByuserId(String id) {
//		根据用户ID来查询对应的权限
//		String s = "select f from Function f left outer join f.roles r left outer join r.users u where u.id = ?";
//		String s = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r left outer join r.users u where u.id = ?"
//		DISTINCT关键字用于排除重复的数据
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ?";
		List<Function> objects = (List<Function>) getHibernateTemplate().find(hql, id);
		return objects;
	}

	@Override
	public List<Function> findallmenu() {
		String s = "from Function f where f.generatemenu = '1' order by f.zindex desc ";
		List<Function> objects = (List<Function>) getHibernateTemplate().find(s);
		return objects;
	}

	//根据用户id查询菜单
	public List<Function> findMenubyUserid(String userId) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1' "
				+ "ORDER BY f.zindex DESC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, userId);
		return list;
	}
}
