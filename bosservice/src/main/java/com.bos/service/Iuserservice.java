package com.bos.service;

import com.bos.domain.User;
import com.bos.utils.PageBean;

public interface Iuserservice {

	User login(User moudele);

	void editPassword(String id, String password);

	void save(User moudele, String[] rowIds);

	void pageQuery(PageBean pageBean);
}
