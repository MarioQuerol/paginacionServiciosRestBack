package com.helloworld.paginacion_servicios_rest.models;

public class Pagination {
	Integer pageNumber;
	Integer pageSize;
	Integer totalResult;

	public  Pagination(){
	}
	
	public Pagination(Integer pageNumber, Integer pageSize, Integer totalResult) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalResult = totalResult;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}
}
