package com.cyj.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.demo.entity.Statistics;
import com.cyj.demo.entity.Statistics;
import com.cyj.demo.service.StatisticsService;
import com.cyj.demo.util.PoiUtils;

@RestController
@RequestMapping("/sta")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService = null;

	private static Calendar now = Calendar.getInstance();

	private PoiUtils poiUtils = new PoiUtils();// 初始化工具类

	/**
	 * 检索查询科室表 http://localhost:8080/SpringBootDemoOne/sta/getAllStatisticsByIds
	 * 
	 * @param teachers
	 * @return
	 */
	@RequestMapping(value = "/getAllStatisticsByIds")
	public Object getAllStatisticsByIds(Statistics sta) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", statisticsService.getStatisticsCount(sta));
		map.put("rows", statisticsService.getAllStatistics(sta));
		return map;
	}

	/**
	 * 下载文件 http://localhost:8080/SpringBootDemoOne/sta/downloadFile?id=1,2,3
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/downloadFile")
	public Object downloadFile(String id, HttpServletResponse response) {// 文件默认下载根路径下的/download目录下
		String downloadName = "导出的日统计数据.xlsx";// 相对当前项目的路径

		List<String> lists = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			lists.add(dids);
		}
		List<Statistics> list = statisticsService.getByStringId(lists);// 要导出的数据集合
		try {
			response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
			response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
			response.setHeader("Content-disposition",
					"attachment;filename=" + new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
			ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流

			poiUtils.createExcel(list, Statistics.class, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示图表
	 * http://localhost:8080/SpringBootDemoOne/sta/getHighChartsData?years=
	 * 2018&month=10&day=8
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/getHighChartsData")
	public Object getHighChartsData(Statistics sta) {
		int year = now.get(Calendar.YEAR);// 获取今天的年
		int months = (now.get(Calendar.MONTH) + 1);// 获取今天的月
		int day = now.get(Calendar.DAY_OF_MONTH);// 获取今天的年日
		
		String year2=""+year;
		
		String months2 = "";
		// 处理月为单数时
		if ((now.get(Calendar.MONTH) + 1) < 10) {
			months2 += "0" + (now.get(Calendar.MONTH) + 1);
		} else {
			months2 += (now.get(Calendar.MONTH) + 1);
		}

		String days2 = "";
		// 处理日为单数时
		if (now.get(Calendar.DAY_OF_MONTH) < 10) {
			days2 += "0" + now.get(Calendar.DAY_OF_MONTH);
		} else {
			days2 += now.get(Calendar.DAY_OF_MONTH);
		}
		
		sta.getStatistics_year();
		sta.getStatistics_month();
		sta.getStatistics_day();
		System.out.println("前台获得的,年:" + sta.getStatistics_year() + "月:" + sta.getStatistics_month() + "日:" + sta.getStatistics_day());
		
		return statisticsService.getHighCharts(sta);
	}

}
