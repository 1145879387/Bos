package com.bos.service;

import com.bos.domain.Role;
import com.bos.utils.PageBean;

import java.util.List;

public interface Iroleservice {
	void save(Role moudele, String functionids);

	void pagequery(PageBean pageBean);

	List<Role> findall();
}
