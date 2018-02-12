package com.bos.dao.base;

import com.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/*
 * 通用接口*/
public interface Ibasedao<T> {
	public void save(T t);

	public void delete(T t);

	public void update(T t);

	public void saveorupdate(T t);

	public T findByid(Serializable id);

	public List<T> findall();

	public List<T> findbycriteria(DetachedCriteria detachedCriteria);

	public void executeupdate(String queryname, Object... objects);

	//
	public void pageQuery(PageBean pageBean);
}
