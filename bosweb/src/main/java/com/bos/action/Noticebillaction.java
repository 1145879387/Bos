package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.Noticebill;
import com.bos.service.Inoticeservice;
import com.bos.utils.crm.Customer;
import com.bos.utils.crm.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class Noticebillaction extends BaseAction<Noticebill> {
	@Autowired
	private ICustomerService iCustomerService;
	@Autowired
	private Inoticeservice iNoticeservice;

	//	根据手机号远程调用crm服务
//  注意，这里传过来的是ajax的键值对，只要键互相对应就可以和栈顶元素里的属性来进行封装
	public String findcustomerByTelephone() throws Exception {
		String telephone = moudele.getTelephone();
		Customer findcustomerbytelephone = iCustomerService.findcustomerbytelephone(telephone);
		javatojson(findcustomerbytelephone, new String[]{});
		return null;
	}

	public String add() throws Exception {
		iNoticeservice.save(moudele);
		return "add";
	}
}
