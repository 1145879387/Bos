package com.bos.service;

import com.bos.domain.Function;
import com.bos.utils.PageBean;

import java.util.List;

public interface Ifunctionservice {
	List<Function> findall();

	void save(Function moudele);

	void pagequery(PageBean pageBean);

	List<Function> findmenu();
}
