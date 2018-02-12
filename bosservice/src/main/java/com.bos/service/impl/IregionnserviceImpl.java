package com.bos.service.impl;

import com.bos.dao.base.IregionnDao;
import com.bos.domain.Region;
import com.bos.service.Iregionnservice;
import com.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IregionnserviceImpl implements Iregionnservice {
	@Override
	public void saveBatch(List<Region> regions) {
		/*区域数据批量保存*/
		for (Region region : regions) {
			iregionnDao.saveorupdate(region);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		iregionnDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findall() {
		return iregionnDao.findall();
	}

	@Override
	public List<Region> findbylist(String q) {
		return iregionnDao.findlistbyq(q);
	}

	@Autowired
	private IregionnDao iregionnDao;
}
