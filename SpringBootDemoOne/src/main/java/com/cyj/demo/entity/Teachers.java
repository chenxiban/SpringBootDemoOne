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
public class Teachers extends LIMIT {
	@PoiHandler(excelIgnore = true)
	private int teachers_id;
	@PoiHandler(excelHeader = "卡号")
	private String teachers_cardNo;
	@PoiHandler(excelHeader = "姓名")
	private String teachers_name;
	@PoiHandler(excelHeader = "性别")
	private String teachers_sex;
	@PoiHandler(excelIgnore = true)
	private int sectionID;
	@PoiHandler(excelIgnore = true)
	private String sections_name;
	@PoiHandler(excelHeader = "当前状态 ")
	private int teachers_status;
	@PoiHandler(excelHeader = "备注")
	private String teachers_remark;

	public static void main(String[] args) {
		System.out.println(new Teachers());
	}

	@Override
	public String toString() {
		return "Teachers [teachers_id=" + teachers_id + ", teachers_cardNo=" + teachers_cardNo + ", teachers_name="
				+ teachers_name + ", teachers_sex=" + teachers_sex + ", sectionID=" + sectionID + ", sections_name="
				+ sections_name + ", teachers_status=" + teachers_status + ", teachers_remark=" + teachers_remark
				+ ", page=" + page + ", rows=" + rows + ", startIndex=" + startIndex + "]";
	}

}
