package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.Function;
import com.bos.service.Ifunctionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class Functionaction extends BaseAction<Function> {
	public String list() throws Exception {
		List<Function> findall = ifunctionservice.findall();
		javatojson(findall, new String[]{"parentFunction", "roles"});
		return null;
	}

	//	添加权限
	public String add() throws Exception {
		Function parentFunction = moudele.getParentFunction();
		if (parentFunction.getId().equals("") && parentFunction != null) {
//			这相当于把外键变成空串
			moudele.setParentFunction(null);
		}
		ifunctionservice.save(moudele);
		return "list";
	}

	public String pageQuery() throws Exception {
		String page = moudele.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		ifunctionservice.pagequery(pageBean);
		javatojson(pageBean, new String[]{"parentFunction", "roles", "children"});
		return null;
	}

	public String findmenu() throws Exception {
		List<Function> findmenu = ifunctionservice.findmenu();
		javatojson(findmenu, new String[]{"parentFunction", "roles", "children"});
		return null;
	}

	@Autowired
	private Ifunctionservice ifunctionservice;
}
