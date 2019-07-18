package com.cyj.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.demo.dao.MembershipsDao;
import com.cyj.demo.entity.Memberships;
import com.cyj.demo.service.MembershipsService;

@Service
public class MembershipsServiceImpl implements MembershipsService {

	@Autowired
	private MembershipsDao membershipsDao;

	@Override
	public List<Memberships> getAllMemberships(Memberships mem) {
		return membershipsDao.getAllMemberships(mem);
	}

	@Override
	public List<Memberships> getAllMembershipsByIds() {
		return membershipsDao.getAllMembershipsByIds();
	}

	@Override
	public Integer getMembershipsCount(Memberships mem) {
		return membershipsDao.getMembershipsCount(mem);
	}

	@Override
	public List<Memberships> getByStringId(List<String> ids) {
		return membershipsDao.getByStringId(ids);
	}
	
}
