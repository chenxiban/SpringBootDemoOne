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
public class Memberships extends LIMIT {

	@PoiHandler(excelIgnore = true)
	private int memberships_id;
	@PoiHandler(excelHeader = "院系")
	private String memberships_department;
	@PoiHandler(excelHeader = "专业")
	private String memberships_specialty;
	@PoiHandler(excelHeader = "学历")
	private String memberships_degree;

	@Override
	public String toString() {
		return "Memberships [memberships_id=" + memberships_id + ", memberships_department=" + memberships_department
				+ ", memberships_specialty=" + memberships_specialty + ", memberships_degree=" + memberships_degree
				+ ", page=" + page + ", rows=" + rows + ", startIndex=" + startIndex + "]";
	}

}
