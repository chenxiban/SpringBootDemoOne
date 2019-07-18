package com.cyj.demo.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cyj.demo.task.MyConsumelogsTest;

/**
 * 
 * 在任务类中使用两个注解@EnableScheduling和@Scheduled(Cron表达式)。
 * 具体作用如下： @EnableScheduling：放在类前，标注启动定时任务； @Scheduled(表达式)：
 * 放在方法前，定义某个 定时任务；
 */

//@Component
//@EnableScheduling
public class SimpleTask {

	@Autowired
	private MyConsumelogsTest myConsumelogsTest;
	@Resource
	private SimpleDateFormat dateFormat;

	//@Scheduled(fixedRate = 250 * 1) // 每隔2.5毫秒
	public void reportCurrentTime() {
		myConsumelogsTest.say();// 执行任务方法
		System.out.println("每隔2.5毫秒任务调度一次 现在时间 " + dateFormat.format(new Date()));
	}
	
	//cron = "*/5 * * * * ? " 表达式表示秒分时日月年。*/5表示每隔5秒。
	@Scheduled(cron = "00 01 23 * * ? ") // 每天23点01触发（cron表达式，六个*【*/5算一个，？算一个】，从左到右分别为秒分时天月年，具体详情可以参考度娘学习）
	public void reportCurrentByCron() {
		System.out.println(
				"每天23点01触发任务调度一次 Scheduling Tasks Examples By Cron:The time is now " + dateFormat.format(new Date()));
	}
	
}
