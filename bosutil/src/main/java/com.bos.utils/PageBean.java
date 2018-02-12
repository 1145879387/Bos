package com.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class PageBean {
	private int currentPage;
	private int pageSize;
	//	总记录书
	private int total;
	//	需要展示的数据结合,注意，这里有subare,建议取消懒加载的方式
	private List rows;
	//	查询条件
	private DetachedCriteria detachedCriteria;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
}
