package com.cyj.demo.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyj.demo.entity.Consumelogs;
import com.cyj.demo.entity.RandomConfig;
import com.cyj.demo.entity.Teachers;
import com.cyj.demo.service.ConsumelogsService;
import com.cyj.demo.service.StudentsService;
import com.cyj.demo.service.TeachersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration// 若为webservlet,则junit需要模拟servletcontext,需给测试类加上@WebAppConfiguration注解
public class TestMyRandomConfig {
	@Autowired
	private RandomConfig randomConfig;
	@Autowired
	private TeachersService t;
	@Autowired
	private StudentsService s;
	// 刷卡记录业务层
	@Autowired
	private ConsumelogsService consumelogsService;

	@Test
	public void a() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(objectMapper.writeValueAsString(randomConfig.getSecret()));
		System.out.println(randomConfig.getIntNumber());
		System.out.println(randomConfig.getLessTen());
		System.out.println(randomConfig.getLongNumber());
		System.out.println(randomConfig.getRange());
		System.out.println(randomConfig.getUuid());
	}

	@Test
	public void getAll() {
		Teachers tea = new Teachers();
		tea.setTeachers_name("马帅帅");
		tea.setPage(1);
		tea.setRows(10);
		tea.setStartIndex(0);
		System.out.println(t.getAllTeachers(tea));
	}

	@Test
	public void getCount() {
		Teachers tea = new Teachers();
		tea.setTeachers_name("马帅帅");
		tea.setPage(1);
		tea.setRows(10);
		tea.setStartIndex(0);
		System.out.println(t.getTeachersCount(tea));
	}

	@Test
	public void add() {
		Teachers tea = new Teachers();
		tea.setTeachers_cardNo("2018003007");
		tea.setTeachers_name("陈小佳佳");
		tea.setTeachers_sex("男");
		tea.setSectionID(1);
		tea.setTeachers_remark("此乃无敌大帅哥小佳佳!!!");
		System.out.println(t.saveTeachers(tea));
	}

	@Test
	public void del() {
		String id = "7";
		List<String> list = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println("list ======>" + list);
		System.out.println(t.delTeachers(list));
	}

	@Test
	public void upd() {
		Teachers tea = new Teachers();
		tea.setTeachers_id(12);
		tea.setTeachers_name("陈小佳");
		tea.setTeachers_remark("此乃无敌大帅哥陈陈小佳佳!!!");
		System.out.println(t.updteTeachers(tea));
	}

	@Test
	public void getStu() {
		System.out.println(s.getList());
	}

	@Test
	public void aaaaa() {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 0标识不在
		Consumelogs con = new Consumelogs();
		con.setConsumelogs_id(1);
		con.setConsumelogs_outTime(formatter.format(new Date()));
		con.setConsumelogs_status(0);
		Boolean boo1 = consumelogsService.updConsumelogs(con);
		System.out.println("consumelogs修改操作结果 ======>" + boo1);
	}
	
	@Test
	public void bbbbbb() {
		List<Consumelogs> list = consumelogsService.getAllConsumelogsByIds();// 要导出的数据集合
		for(int i = 0; i < 1; i++) {
			System.out.println("查询的集合为================>"+list.get(i));
		}
	}

}
