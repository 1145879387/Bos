package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.Decidedzone;
import com.bos.service.IDecidedzoneservice;
import com.bos.utils.crm.Customer;
import com.bos.utils.crm.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class Decidedzoneaction extends BaseAction<Decidedzone> {
	//	属性驱动来接收多个分区id
	public String[] subareaid;
	@Autowired
	private ICustomerService iCustomerService;

	//默认提供set方法就行，get可以不用写
	public String[] getSubareaid() {
		return subareaid;
	}

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	public String add() throws Exception {
		iDecidedzoneservice.save(moudele, subareaid);
		return "list";
	}

	public String findlistnotassaction() throws Exception {
		List<Customer> findlistnotassaction = iCustomerService.findlistnotassaction();
		javatojson(findlistnotassaction, new String[]{});
		return null;
	}

	public String findlisthasassaction() throws Exception {
		String id = moudele.getId();
		List<Customer> findlisthasassaction = iCustomerService.findlisthasassaction(id);
		javatojson(findlisthasassaction, new String[]{});
		return null;
	}

	public String pageQuery() throws Exception {
		iDecidedzoneservice.pageQuery(pageBean);
		javatojson(pageBean, new String[]{"subareas", "currentPage", "detachedCriteria", "pageSize", "decidedzones"});
		return null;
	}

	public String assigncustomerstodecidedzone() throws Exception {
		iCustomerService.assigncustomerstodecidedzone(moudele.getId(), customerIds);
		return "list";
	}

	@Autowired
	private IDecidedzoneservice iDecidedzoneservice;
	//	注意，这里即使写成list也阔以接收，struts会自动封装其中的类型
	private List<Integer> customerIds;

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
}
