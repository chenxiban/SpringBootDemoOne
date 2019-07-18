package com.cyj.demo.dao;

import java.util.List;

import com.cyj.demo.entity.Readrooms;

public interface ReadroomsDao {
	
	/**
	 * 查询所有阅览室信息
	 * @return
	 */
	public List<Readrooms> getAllReadrooms(Readrooms rea);// 分页查询
	
	/**
	 * 查询所有阅览室信息
	 * @return
	 */
	public List<Readrooms> getAllReadroomsByIds();
	
	/**
	 * 查询所有阅览室总数
	 * @return
	 */
	public Integer getReadroomsCount(Readrooms rea);
	
	/**
	 * 根据ids查询阅览室
	 * @return
	 */
	public List<Readrooms> getByStringId(List<String> ids);
	
}
