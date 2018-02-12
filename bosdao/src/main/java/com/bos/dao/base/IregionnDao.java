package com.bos.dao.base;

import com.bos.domain.Region;

import java.util.List;

public interface IregionnDao extends Ibasedao<Region> {
	List<Region> findlistbyq(String q);
}
