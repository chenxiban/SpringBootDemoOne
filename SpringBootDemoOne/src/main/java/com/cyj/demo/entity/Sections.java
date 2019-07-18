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
public class Sections extends LIMIT {
	@PoiHandler(excelIgnore = true)
	private int sections_id;
	@PoiHandler(excelHeader = "科室名称")
	private String sections_name;
	@PoiHandler(excelHeader = "科室备注")
	private String sections_remark;
	
	@Override
	public String toString() {
		return "Sections [sections_id=" + sections_id + ", sections_name=" + sections_name + ", sections_remark="
				+ sections_remark + ", page=" + page + ", rows=" + rows + ", startIndex=" + startIndex + "]";
	}
	
}
