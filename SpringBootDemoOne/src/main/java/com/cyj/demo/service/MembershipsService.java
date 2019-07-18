package com.cyj.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyj.demo.entity.Memberships;

@Service
public interface MembershipsService {
	
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
