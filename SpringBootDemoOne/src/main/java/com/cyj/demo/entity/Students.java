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
public class Students extends LIMIT {
	@PoiHandler(excelIgnore = true)
	private int students_id;
	@PoiHandler(excelHeader = "卡号")
	private String students_cardNo;
	@PoiHandler(excelHeader = "姓名")
	private String students_name;
	@PoiHandler(excelHeader = "性别")
	private String students_sex;
	@PoiHandler(excelHeader = "所属院系编号")
	private int membershipId;
	@PoiHandler(excelIgnore = true)
	private String memberships_department;// 学生所在院
	@PoiHandler(excelIgnore = true)
	private String memberships_specialty;// 学生所在系
	@PoiHandler(excelHeader = "学生当前状态")
	private int students_status;
	@PoiHandler(excelHeader = "学生备注")
	private String students_remark;
	@PoiHandler(excelHeader = "学号")
	private String students_stuNo;
	
	@Override
	public String toString() {
		return "Students [students_id=" + students_id + ", students_cardNo=" + students_cardNo + ", students_name="
				+ students_name + ", students_sex=" + students_sex + ", membershipId=" + membershipId
				+ ", memberships_department=" + memberships_department + ", memberships_specialty="
				+ memberships_specialty + ", students_status=" + students_status + ", students_remark="
				+ students_remark + ", students_stuNo=" + students_stuNo + ", page=" + page + ", rows=" + rows
				+ ", startIndex=" + startIndex + "]";
	}
	
}
