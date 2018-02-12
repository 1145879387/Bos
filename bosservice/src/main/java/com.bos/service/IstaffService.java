package com.bos.service;

import com.bos.domain.Staff;
import com.bos.utils.PageBean;

import java.util.List;

public interface IstaffService {
	void save(Staff moudele);

	void pageQuery(PageBean pageBean);

	void deleteBatch(String ids);

	Staff findbyid(String id);

	void update(Staff staff);

	List<Staff> findlistnotdelete();
}
