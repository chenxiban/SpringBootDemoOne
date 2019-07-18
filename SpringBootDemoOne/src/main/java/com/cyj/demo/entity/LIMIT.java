package com.cyj.demo.entity;

import java.io.Serializable;

import com.cyj.demo.util.PoiHandler;

public class LIMIT implements Serializable {
	// ---------------------------分页参数----------------------------------
	// protected允许子类继承
	@PoiHandler(excelIgnore=true)
	protected Integer page = 0;// 当前第几页
	@PoiHandler(excelIgnore=true)
	protected Integer rows = 10;// 每页大小
	@PoiHandler(excelIgnore=true)
	protected Integer startIndex = 0;// Mysql分页查询limit第一个参数

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStartIndex() {
		return (page - 1) * rows;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

}
