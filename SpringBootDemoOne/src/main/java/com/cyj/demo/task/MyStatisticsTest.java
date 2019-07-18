package com.cyj.demo.task;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.cyj.demo.entity.Consumelogs;
import com.cyj.demo.entity.Readrooms;
import com.cyj.demo.entity.Statistics;
import com.cyj.demo.service.ConsumelogsService;
import com.cyj.demo.service.ReadroomsService;
import com.cyj.demo.service.StatisticsService;
import com.cyj.demo.service.impl.ConsumelogsServiceImpl;

//@Component
public class MyStatisticsTest {
	
	// 刷卡记录业务层
	@Autowired
	private static ConsumelogsService consumelogsService = new ConsumelogsServiceImpl();
	// 阅览室业务层
	@Autowired
	private static ReadroomsService readroomsService;
	// 日统计业务层
	@Autowired
	private static StatisticsService statisticsService;
	
	private static Calendar now = Calendar.getInstance();
	
	public void run() { 
		int year = now.get(Calendar.YEAR);// 获取今天的年
		int month = (now.get(Calendar.MONTH) + 1);// 获取今天的月
		int day = now.get(Calendar.DAY_OF_MONTH);// 获取今天的年日
		String years=""+year;
		
		String months = "";
		// 处理月为单数时
		if ((now.get(Calendar.MONTH) + 1) < 10) {
			months += "0" + (now.get(Calendar.MONTH) + 1);
		} else {
			months += (now.get(Calendar.MONTH) + 1);
		}

		String days = "";
		// 处理日为单数时
		if (now.get(Calendar.DAY_OF_MONTH) < 10) {
			days += "0" + now.get(Calendar.DAY_OF_MONTH);
		} else {
			days += now.get(Calendar.DAY_OF_MONTH);
		}

		System.out.print("年: " + now.get(Calendar.YEAR) + "\t");
		System.out.print("月: " + (now.get(Calendar.MONTH) + 1) + "\t");
		System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
		
		// 定义查询需要使用的date日期格式
		String data = year + "-" + months + "-" + days;
		System.out.println(data);
		try {
			// 防止多线程过于争抢资源
			Thread.sleep(new Random().nextInt(2000));
			// 查询所有ReadroomsId
			List<Readrooms> reaList=readroomsService.getAllReadroomsByIds();
			List<Consumelogs> conList1= null;
			for (int i = 0; i < reaList.size(); i++) {
				// 根据ReadroomsId分组并且求使用次数getStatistics_peopleNums
				conList1 = consumelogsService.getByCount(reaList.get(i).getReadrooms_id(),data);
				// 如果根据这个阅览室id查到的为空
				if (conList1==null || conList1.size()==0) {
					//StatisticsBuilder sta1=Statistics.builder().readRoomId(reaList.get(i).getReadrooms_id()).statistics_peopleNums(0).statistics_year(years).statistics_month(months).statistics_day(days);
					Statistics sta = new Statistics();
					sta.setReadRoomId(reaList.get(i).getReadrooms_id());
					sta.setStatistics_peopleNums(0);
					sta.setStatistics_year(years);
					sta.setStatistics_month(months);
					sta.setStatistics_day(days);
					Boolean boo = statisticsService.saveStatistics(sta);
					System.out.println("日统计记录表添加,录入结果为:" + boo);
				}else {
					Statistics sta = new Statistics();
					sta.setReadRoomId(reaList.get(i).getReadrooms_id());
					sta.setStatistics_peopleNums(conList1.get(0).getStatistics_peopleNums());
					sta.setStatistics_year(years);
					sta.setStatistics_month(months);
					sta.setStatistics_day(days);
					Boolean boo = statisticsService.saveStatistics(sta);
					System.out.println("日统计记录表添加,录入结果为:" + boo);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("sayHello执行了...");
	}
	
}
