package com.cyj.demo.entity;

import com.cyj.demo.util.PoiHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder// 使用建造模型
@JsonInclude(Include.NON_NULL)
public class Readrooms extends LIMIT {
	
	@PoiHandler(excelIgnore = true)
	private int readrooms_id;
	@PoiHandler(excelHeader = "阅览室名称")
	private String readrooms_name;
	@PoiHandler(excelHeader = "备注")
	private String readrooms_remark;
	
	@Override
	public String toString() {
		return "Readrooms [readrooms_id=" + readrooms_id + ", readrooms_name=" + readrooms_name + ", readrooms_remark="
				+ readrooms_remark + ", page=" + page + ", rows=" + rows + ", startIndex=" + startIndex + "]";
	}
	
}
