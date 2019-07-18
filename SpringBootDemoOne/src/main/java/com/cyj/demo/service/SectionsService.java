package com.cyj.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyj.demo.entity.Sections;

@Service
public interface SectionsService {
	
	/**
	 * 添加科室信息
	 * @return
	 */
	public boolean saveSections(Sections tea);
	
	/**
	 * 删除科室信息
	 * @return
	 */
	public boolean delSections(List<String> ids);
	
	/**
	 * 修改科室信息
	 * @return
	 */
	public boolean updteSections(Sections tea);
	
	/**
	 * 查询所有科室信息
	 * @return
	 */
	public List<Sections> getAllSections(Sections tea);
	
	/**
	 * 根据String ids查询科室信息
	 * @param ids
	 * @return
	 */
	public List<Sections> getByStringId(List<String> ids);
	
	/**
	 * 查询所有科室信息
	 * @return
	 */
	public List<Sections> getAllSectionsByIds();
	
	/**
	 * 查询所有科室总数
	 * @return
	 */
	public Integer getSectionsCount(Sections tea);
	
}
