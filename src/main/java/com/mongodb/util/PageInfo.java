package com.mongodb.util;

import java.util.List;

/**
 * 
 * @author StoneCai
 * 2016年07月26日16:35:02
 * 添加
 * 分页类
 */
public class PageInfo {
	private int pageSize=10;
	private int currentPage=1;
	private long total;
	private List<Object> list;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
}
