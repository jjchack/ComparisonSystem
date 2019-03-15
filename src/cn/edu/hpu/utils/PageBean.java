package cn.edu.hpu.utils;

import java.util.List;

public class PageBean<T> {

	private int currentPage = 1;
	private int pageSize = 5;
	private int totalCount;
	private int totalPage;
	private List<T> pageData;
	
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		if (totalCount % pageSize == 0) {
			totalPage = totalCount/pageSize;
		} else {
			totalPage = totalCount/pageSize + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageDate) {
		this.pageData = pageDate;
	}
	
}
