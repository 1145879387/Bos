package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.Workordermanage;
import com.bos.service.IworkerService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class Workerdoermanager extends BaseAction<Workordermanage> {
	public String add() throws Exception {
		String f = "1";
		try {
			iworkerService.save(moudele);
		} catch (Exception e) {
			f = "0";
		}
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(f);
		return null;
	}

	@Autowired
	private IworkerService iworkerService;
}
