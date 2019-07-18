package com.cyj.demo.dao;

import java.util.List;

import com.cyj.demo.entity.Memberships;

public interface MembershipsDao {
	
	/**
	 * 查询所有院系表信息
	 * @return
	 */
	public List<Memberships> getAllMemberships(Memberships mem);// 分页查询
	
	/**
	 * 查询所有院系表信息
	 * @return
	 */
	public List<Memberships> getAllMembershipsByIds();
	
	/**
	 * 查询所有院系表总数
	 * @return
	 */
	public Integer getMembershipsCount(Memberships mem);
	
	/**
	 * 根据ids查询院系表
	 * @return
	 */
	public List<Memberships> getByStringId(List<String> ids);
	
}
