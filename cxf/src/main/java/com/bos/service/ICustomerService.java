package com.bos.service;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ICustomerService {
	public List<Customer> findAll();

	//	查询未关联定区的客户
	public List<Customer> findlistnotassaction();

	//	查询已经关联定区的客户
	public List<Customer> findlisthasassaction(String decidedzoneid);

	public void assigncustomerstodecidedzone(String decidedzoneid, Integer[] ids);

	public String findbydecidAddress(String address);

	public Customer findcustomerbytelephone(String telephone);
}
