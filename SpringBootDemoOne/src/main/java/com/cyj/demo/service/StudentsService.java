package com.cyj.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyj.demo.entity.Students;

@Service
public interface StudentsService {
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	public List<Students> getAllStudents(Students stu);// 分页查询
	
	/**
	 * 查询所有学生总数
	 * @return
	 */
	public Integer getStudentsCount(Students stu);
	
	/**
	 * 根据ids查询学生
	 * @return
	 */
	public List<Students> getByStringId(List<String> ids);
	
	/**
	  *  查询所有学生集合
	 * @return
	 */
	public List<Students> getList();
	
	/**
	  *  修改学生信息
	 * @return
	 */
	public boolean updStudents(Students stu);
	
}
