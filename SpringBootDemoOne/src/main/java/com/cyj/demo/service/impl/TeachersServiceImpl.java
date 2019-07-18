package com.cyj.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.demo.dao.TeachersDao;
import com.cyj.demo.entity.Teachers;
import com.cyj.demo.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService {
	
	@Autowired
	private TeachersDao TeachersDao;
	
	@Override
	public boolean saveTeachers(Teachers tea) {
		return TeachersDao.saveTeachers(tea) > 0 ? true:false;
	}

	@Override
	public boolean delTeachers(List<String> ids) {
		return TeachersDao.delTeachers(ids) > 0 ? true:false;
	}

	@Override
	public boolean updteTeachers(Teachers tea) {
		return TeachersDao.updteTeachers(tea) > 0 ? true:false;
	}

	@Override
	public List<Teachers> getAllTeachers(Teachers tea) {
		return TeachersDao.getAllTeachers(tea);
	}

	@Override
	public Integer getTeachersCount(Teachers tea) {
		return TeachersDao.getTeachersCount(tea);
	}

	public List<Teachers> getByStringId(List<String> ids) {
		return TeachersDao.getByStringId(ids);
	}

	@Override
	public List<Teachers> getList() {
		return TeachersDao.getList();
	}
	
}
