package com.cyj.demo.entity;

import com.cyj.demo.util.PoiHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data// 自动生成set方法,自动生成get方法
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder// 使用建造模型
@JsonInclude(Include.NON_NULL)
public class Consumelogs extends LIMIT {
	
	@PoiHandler(excelIgnore = true)
	private int consumelogs_id;
	@PoiHandler(excelHeader = "资源编号")
	private String consumelogs_cardNo;
	@PoiHandler(excelHeader = "阅览室")
	private int readRoomId;
	@PoiHandler(excelIgnore = true)
	private String readrooms_name;// 阅览室名称
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@PoiHandler(excelHeader = "进入时间")
	private String consumelogs_inTime;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@PoiHandler(excelHeader = "离开时间")
	private String consumelogs_outTime;
	@PoiHandler(excelHeader = "当前状态")
	private int consumelogs_status;
	@PoiHandler(excelIgnore = true)
	private int statistics_peopleNums;
	
	//这里是辅助字段
	@PoiHandler(excelIgnore = true)
	private String tests;// 判断老师还是学生
	@PoiHandler(excelIgnore = true)
	private String students_name;// 学生名字连表查询用
	@PoiHandler(excelIgnore = true)
	private String teachers_name;// 老师名字连表查询用
	@PoiHandler(excelIgnore = true)
	private String years;// 时间
	@PoiHandler(excelIgnore = true)
	private String month;// 时间
	@PoiHandler(excelIgnore = true)
	private String dateString;// sql语句
	@PoiHandler(excelIgnore = true)
	private String name;// sql语句
	@PoiHandler(excelIgnore = true)
	private String data;// sql语句
	
	//时间查询辅助字段
	@PoiHandler(excelIgnore=true)
	private String startTime;// 进入日期开始范围
	@PoiHandler(excelIgnore=true)
	private String endTime;// 进入日期结束范围
	
	@PoiHandler(excelIgnore=true)
	private String startTimes;// 离开日期开始范围
	@PoiHandler(excelIgnore=true)
	private String endTimes;// 离开日期结束范围
	
	@Override
	public String toString() {
		return "Consumelogs [consumelogs_id=" + consumelogs_id + ", consumelogs_cardNo=" + consumelogs_cardNo
				+ ", readRoomId=" + readRoomId + ", readrooms_name=" + readrooms_name + ", consumelogs_inTime="
				+ consumelogs_inTime + ", consumelogs_outTime=" + consumelogs_outTime + ", consumelogs_status="
				+ consumelogs_status + ", startTime=" + startTime + ", endTime=" + endTime + ", startTimes="
				+ startTimes + ", endTimes=" + endTimes + ", page=" + page + ", rows=" + rows + ", startIndex="
				+ startIndex + "]";
	}

}
