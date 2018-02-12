package com.bos.service;

import com.bos.domain.Region;
import com.bos.utils.PageBean;

import java.util.List;

public interface Iregionnservice {
	void saveBatch(List<Region> regions);

	void pageQuery(PageBean pageBean);

	List<Region> findall();

	List<Region> findbylist(String q);
}
