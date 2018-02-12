package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.Role;
import com.bos.service.Iroleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class Roleaction extends BaseAction<Role> {
	private String functionids;

	//  只有页面回显的时候会用到get方法，赋值的时候用的是set方法
	public void setFunctionids(String functionids) {
		this.functionids = functionids;
	}
//	添加角色的方法

	public String add() throws Exception {
		iroleservice.save(moudele, functionids);
		return null;
	}

	public String pageQuery() throws Exception {
		iroleservice.pagequery(pageBean);
		javatojson(pageBean, new String[]{"functions", "users"});
		return null;
	}

	public String listajax() throws Exception {
//		查询所有的角色数据，返回json
		List<Role> findall = iroleservice.findall();
		javatojson(findall, new String[]{"functions", "users"});
		return null;
	}

	@Autowired
	private Iroleservice iroleservice;
}
