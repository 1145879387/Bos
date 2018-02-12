package com.bos.dao.base;

import com.bos.domain.Subarea;

import java.util.List;

public interface IsubareaDao extends Ibasedao<Subarea> {

	List<Object[]> findbysubarea();
}
