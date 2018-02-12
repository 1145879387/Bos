package com.bos.dao.base.impl;

import com.bos.dao.base.Ibasedao;
import com.bos.utils.PageBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class IbasedaoImpl<T> extends HibernateDaoSupport implements Ibasedao<T> {
	//	T的值如何获取？
	private Class<T> tClass;

	// 在父类的构造中动态获取T
	public IbasedaoImpl() {
//		必须有泛型才可以强转
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
//		获得运行时类型后强转
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		tClass = (Class<T>) actualTypeArguments[0];
	}

	@Resource(name = "sessionFactory")
	public void setmysessionfactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public void saveorupdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public T findByid(Serializable id) {
		return getHibernateTemplate().get(tClass, id);
	}

	@Override
	public List<T> findall() {
		String s = "from " + tClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(s);
	}

	@Override
	public List<T> findbycriteria(DetachedCriteria detachedCriteria) {
		return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public void executeupdate(String queryname, Object... objects) {
		Session currentSession = getSessionFactory().getCurrentSession();
//		获取在hbm标签里的query语句，获得注意query语句不能写class标签里面，而应该和class标签并列，这样才能读取到
		Query namedQuery = currentSession.getNamedQuery(queryname);
		int length = 0;
		for (Object object : objects) {
			namedQuery.setParameter(length++, object);
		}
		namedQuery.executeUpdate();
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
//		查询total
		detachedCriteria.setProjection(Projections.rowCount());
//		执行hql语句
		List<Long> byCriteria = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long aLong = byCriteria.get(0);
		pageBean.setTotal(aLong.intValue());
//		查询rows
		detachedCriteria.setProjection(null);
//		指定hibnate框架封装对象的方式
//		注意，这是标准封装，否则hibnate会将数据和对象包装进一个object数组来进行封装，但是这样返回的就是一个数组，所以我应该调用这行代码，把数据封装成属性嵌套成属性这种格式来进行封装
		detachedCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		int a = (currentPage - 1) * pageSize;
		int pageSize1 = pageSize;
//		查询条件，这其实是一个分页查询，用于显示数据，注意，数据其实是从前台传递过来的
		List byCriteria1 = getHibernateTemplate().findByCriteria(detachedCriteria, a, pageSize1);
//		注意，转换成json的时候是rows存储的是moudle(struts栈顶元素)的一个对象，所以在转换json的时候会去解析rows里面的数据，所以相关联的类也需要排除
		pageBean.setRows(byCriteria1);
	}
}
