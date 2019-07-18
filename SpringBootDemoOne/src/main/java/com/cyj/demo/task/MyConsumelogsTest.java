package com.cyj.demo.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyj.demo.entity.Consumelogs;
import com.cyj.demo.entity.Readrooms;
import com.cyj.demo.entity.Students;
import com.cyj.demo.entity.Teachers;
import com.cyj.demo.service.ConsumelogsService;
import com.cyj.demo.service.ReadroomsService;
import com.cyj.demo.service.StudentsService;
import com.cyj.demo.service.TeachersService;

@Component
public class MyConsumelogsTest {

	// 学生业务层
	@Autowired
	private StudentsService studentsService;
	// 老师业务层
	@Autowired
	private TeachersService teachersService;
	// 阅览室业务层
	@Autowired
	private ReadroomsService readroomsService;
	// 刷卡记录业务层
	@Autowired
	private ConsumelogsService consumelogsService;
	
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void say() {
		int flag = new Random().nextInt(2) + 1;
		int status = 0;
		List<Readrooms> reaList = readroomsService.getAllReadroomsByIds();// 获得阅览室集合
		Random random = new Random();
		int a = random.nextInt(reaList.size());
		reaList.get(a).getReadrooms_id();// 随机阅览室id

		if (flag == 1) {
			List<Students> stuList = studentsService.getList();// 获得学生集合
			Random random1 = new Random();
			int b = random1.nextInt(stuList.size());
			stuList.get(b).getStudents_id();// 随机学生id
			stuList.get(b).getStudents_cardNo();// 随机学生的卡号
			stuList.get(b).getStudents_status();// 随机学生的状态

			// 刷卡记录集合
			List<Consumelogs> conList = consumelogsService.getByCardNo(stuList.get(b).getStudents_cardNo());
			for (int i = 0; i < conList.size(); i++) {
				if (conList.get(i).getConsumelogs_status() != 0) {
					status = conList.get(i).getConsumelogs_id();// 获得当前在阅览室的卡号id刷卡记录id
				}
			}

			System.out.println("status ======>" + status);

			if (stuList.get(b).getStudents_status() != 0) {// 若学生状态不为0则正在阅览室,修改学生状态,添加离开刷卡记录
				Students stu = new Students();
				stu.setStudents_id(stuList.get(b).getStudents_id());
				stu.setStudents_status(0);
				Boolean boo = studentsService.updStudents(stu);
				System.out.println("students修改操作结果 ======>" + boo);

				// 0标识不在
				Consumelogs con = new Consumelogs();
				con.setConsumelogs_id(status);
				con.setConsumelogs_outTime(formatter.format(new Date()));
				con.setConsumelogs_status(0);
				Boolean boo1 = consumelogsService.updConsumelogs(con);
				System.out.println("consumelogs修改操作结果 ======>" + boo1);
			} else {
				Students stu = new Students();
				stu.setStudents_id(stuList.get(b).getStudents_id());
				stu.setStudents_status(reaList.get(a).getReadrooms_id());
				Boolean boo = studentsService.updStudents(stu);
				System.out.println("students修改操作结果 ======>" + boo);

				// 1标识不在
				Consumelogs con = new Consumelogs();
				con.setConsumelogs_cardNo(stuList.get(b).getStudents_cardNo());
				con.setConsumelogs_inTime(formatter.format(new Date()));
				con.setReadRoomId(reaList.get(a).getReadrooms_id());
				con.setConsumelogs_status(1);
				Boolean boo1 = consumelogsService.saveConsumelogs(con);
				System.out.println("consumelogs添加操作结果 ======>" + boo1);
			}
		} else {
			List<Teachers> teaList = teachersService.getList();// 获得老师集合
			Random random2 = new Random();
			int c = random2.nextInt(teaList.size());
			teaList.get(c).getTeachers_id();// 随机老师id
			teaList.get(c).getTeachers_cardNo();// 随机老师的卡号
			teaList.get(c).getTeachers_status();// 随机老师的状态

			// 刷卡记录集合
			List<Consumelogs> conList = consumelogsService.getByCardNo(teaList.get(c).getTeachers_cardNo());
			for (int i = 0; i < conList.size(); i++) {
				if (conList.get(i).getConsumelogs_status() != 0) {
					status = conList.get(i).getConsumelogs_id();// 获得当前在阅览室的卡号id刷卡记录id
				}
			}

			System.out.println("status ======>" + status);

			if (teaList.get(c).getTeachers_status() != 0) {// 若老师状态不为0则正在阅览室,修改老师状态,添加离开刷卡记录
				Teachers tea = new Teachers();
				tea.setTeachers_id(teaList.get(c).getTeachers_id());
				tea.setTeachers_status(0);
				Boolean boo = teachersService.updteTeachers(tea);
				System.out.println("Teachers修改操作结果 ======>" + boo);

				// 0标识不在
				Consumelogs con = new Consumelogs();
				con.setConsumelogs_id(status);
				con.setConsumelogs_outTime(formatter.format(new Date()));
				con.setConsumelogs_status(0);
				Boolean boo1 = consumelogsService.updConsumelogs(con);
				System.out.println("consumelogs修改操作结果 ======>" + boo1);
			} else {// 若老师状态为0则正常,修改老师状态,添加刷卡记录
				Teachers tea = new Teachers();
				tea.setTeachers_id(teaList.get(c).getTeachers_id());
				tea.setTeachers_status(reaList.get(a).getReadrooms_id());
				Boolean boo = teachersService.updteTeachers(tea);
				System.out.println("Teachers修改操作结果 ======>" + boo);

				// 1标识不在
				Consumelogs con = new Consumelogs();
				con.setConsumelogs_cardNo(teaList.get(c).getTeachers_cardNo());
				con.setConsumelogs_inTime(formatter.format(new Date()));
				con.setReadRoomId(reaList.get(a).getReadrooms_id());
				con.setConsumelogs_status(1);
				Boolean boo1 = consumelogsService.saveConsumelogs(con);
				System.out.println("consumelogs添加操作结果 ======>" + boo1);
			}
		}
	}

}
