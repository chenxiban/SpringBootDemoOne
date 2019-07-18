package com.cyj.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.demo.dao.StudentsDao;
import com.cyj.demo.entity.Students;
import com.cyj.demo.service.StudentsService;

@Service
public class StudentsServiceImpl implements StudentsService {
	
	@Autowired
	private StudentsDao studentsDao;

	@Override
	public List<Students> getAllStudents(Students stu) {
		return studentsDao.getAllStudents(stu);
	}

	@Override
	public Integer getStudentsCount(Students stu) {
		return studentsDao.getStudentsCount(stu);
	}

	@Override
	public List<Students> getByStringId(List<String> ids) {
		return studentsDao.getByStringId(ids);
	}

	@Override
	public List<Students> getList() {
		return studentsDao.getList();
	}

	@Override
	public boolean updStudents(Students stu) {
		return studentsDao.updStudents(stu)>0 ? true:false;
	}
	
	
	
	
}
