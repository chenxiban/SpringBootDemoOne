package com.cyj.demo.dao;

import java.util.List;

import com.cyj.demo.entity.Students;

public interface StudentsDao {
	
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
	public Integer updStudents(Students stu);
	
}
