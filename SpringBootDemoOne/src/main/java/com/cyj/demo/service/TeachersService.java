package com.cyj.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyj.demo.entity.Teachers;

@Service
public interface TeachersService {
	
	/**
	 * 添加老师信息
	 * @return
	 */
	public boolean saveTeachers(Teachers tea);
	
	/**
	 * 删除老师信息
	 * @return
	 */
	public boolean delTeachers(List<String> ids);
	
	/**
	 * 修改老师信息
	 * @return
	 */
	public boolean updteTeachers(Teachers tea);
	
	/**
	 * 查询所有老师信息
	 * @return
	 */
	public List<Teachers> getAllTeachers(Teachers tea);
	
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
