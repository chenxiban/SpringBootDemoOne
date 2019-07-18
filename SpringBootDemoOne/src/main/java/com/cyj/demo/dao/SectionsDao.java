package com.cyj.demo.dao;

import java.util.List;

import com.cyj.demo.entity.Sections;

public interface SectionsDao {
	
	/**
	 * 添加科室信息
	 * @return
	 */
	public Integer saveSections(Sections tea);
	
	/**
	 * 删除科室信息
	 * @return
	 */
	public Integer delSections(List<String> ids);
	
	/**
	 * 修改科室信息
	 * @return
	 */
	public Integer updteSections(Sections tea);
	
	/**
	 * 查询所有科室信息
	 * @return
	 */
	public List<Sections> getAllSections(Sections tea);// 分页查询
	
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
