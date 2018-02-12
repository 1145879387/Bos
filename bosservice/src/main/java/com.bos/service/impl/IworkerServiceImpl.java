package com.bos.service.impl;

import com.bos.dao.base.IworkerDao;
import com.bos.domain.Workordermanage;
import com.bos.service.IworkerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IworkerServiceImpl implements IworkerService<Workordermanage> {

	@Override
	public void save(Workordermanage moudele) {
		iworkerDao.save(moudele);
	}

	private IworkerDao iworkerDao;
}
