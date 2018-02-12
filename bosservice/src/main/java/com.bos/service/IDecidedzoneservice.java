package com.bos.service;

import com.bos.domain.Decidedzone;
import com.bos.utils.PageBean;

public interface IDecidedzoneservice {
	void save(Decidedzone moudele, String[] subareaid);

	void pageQuery(PageBean pageBean);
}
