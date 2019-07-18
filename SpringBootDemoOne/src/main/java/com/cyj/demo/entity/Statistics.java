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
public class Statistics extends LIMIT {
	
	@PoiHandler(excelIgnore = true)
	private int statistics_id;
	@PoiHandler(excelHeader = "阅览室ID")
	private int readRoomId;
	@PoiHandler(excelIgnore = true)
	private String readrooms_name;// 阅览室名称
	@PoiHandler(excelHeader = "资源使用次数")
	private int statistics_peopleNums;
	@PoiHandler(excelHeader = "年")
	private String statistics_year;
	@PoiHandler(excelHeader = "月")
	private String statistics_month;
	@PoiHandler(excelHeader = "日")
	private String statistics_day;
	
	@Override
	public String toString() {
		return "Statistics [statistics_id=" + statistics_id + ", readRoomId=" + readRoomId + ", readrooms_name="
				+ readrooms_name + ", statistics_peopleNums=" + statistics_peopleNums + ", statistics_year="
				+ statistics_year + ", statistics_month=" + statistics_month + ", statistics_day=" + statistics_day
				+ ", page=" + page + ", rows=" + rows + ", startIndex=" + startIndex + "]";
	}
	
}
