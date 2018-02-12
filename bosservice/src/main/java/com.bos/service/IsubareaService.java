package com.bos.service;

import com.bos.domain.Subarea;
import com.bos.utils.PageBean;

import java.util.List;

public interface IsubareaService {
	void save(Subarea moudele);

	void pagequery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> findListNotassociatiion();

	List<Subarea> findlistbydecidedid(String decidedzoneId);

	List<Object[]> findSubareasGroup();
}
