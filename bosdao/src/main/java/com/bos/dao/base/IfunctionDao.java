package com.bos.dao.base;

import com.bos.domain.Function;

import java.util.List;

public interface IfunctionDao extends Ibasedao<Function> {
	@Override
	List<Function> findall();

	List<Function> findfunctionByuserId(String id);

	List<Function> findallmenu();

	List<Function> findMenubyUserid(String userId);
}
