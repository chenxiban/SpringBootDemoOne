package com.cyj.demo.dao;

import java.util.List;

import com.cyj.demo.entity.Teachers;

public interface TeachersDao {
	
	/**
	 * 添加老师信息
	 * @return
	 */
	public int saveTeachers(Teachers tea);
	
	/**
	 * 删除老师信息
	 * @return
	 */
	public int delTeachers(List<String> ids);
	
	/**
	 * 修改老师信息
	 * @return
	 */
	public int updteTeachers(Teachers tea);
	
	/**
	 * 查询所有老师信息
	 * @return
	 */
	public List<Teachers> getAllTeachers(Teachers tea);// 分页查询
	
	/**
	 * 根据String ids查询老师信息
	 * @param ids
	 * @return
	 */
	public List<Teachers> getByStringId(List<String> ids);
	
	/**
	 * 查询所有老师总数
	 * @return
	 */
	public Integer getTeachersCount(Teachers tea);
	
	/**
	 * 查询老师信息
	 * @param ids
	 * @return
	 */
	public List<Teachers> getList();

}
