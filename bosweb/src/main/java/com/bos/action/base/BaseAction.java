package com.bos.action.base;

import com.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	//	需要一个模型对象
	protected T moudele;

	@Override
	public T getModel() {
		return moudele;
	}

	protected PageBean pageBean = new PageBean();
	DetachedCriteria detachedCriteria = null;

	//在进行赋值的时候会直接调用set方法赋值给page,所以Page属性就没啥用了
	public void setPage(int page) {
		//		注意，staff里的属性要和前台colums的filed的字段一致！否则很有可能会无法显示数据
		//	当前页数
		pageBean.setCurrentPage(page);
	}

	public void javatojson(Object o, String[] exStrings) {
		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
		jsonConfig.setExcludes(exStrings);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
//		注意，必须要设置jsonconfig的参数，不然相当于没排除
		String s = JSONObject.fromObject(o, jsonConfig).toString();
		try {
//			注意，这个类是通用的类，如果这个方法抛出异常，则所有的子类都要Try
			System.out.println(s);
			ServletActionContext.getResponse().getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void javatojson(List o, String[] exStrings) {
		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
		jsonConfig.setExcludes(exStrings);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		String s = JSONArray.fromObject(o, jsonConfig).toString();
		try {
//			注意，这个类是通用的类，如果这个方法抛出异常，则所有的子类都要Try
			System.out.println(s);
			ServletActionContext.getResponse().getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void setRows(int rows) {
		//	每页显示多少条数据
		pageBean.setPageSize(rows);
	}

	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
//		通过这段代码来获得T的类型，然后强转成class对象
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> actualTypeArgument = (Class<T>) actualTypeArguments[0];
		detachedCriteria = DetachedCriteria.forClass(actualTypeArgument);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			moudele = actualTypeArgument.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
