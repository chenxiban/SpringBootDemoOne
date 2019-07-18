package com.cyj.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyj.demo.entity.Readrooms;

@Service
public interface ReadroomsService {
	
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
