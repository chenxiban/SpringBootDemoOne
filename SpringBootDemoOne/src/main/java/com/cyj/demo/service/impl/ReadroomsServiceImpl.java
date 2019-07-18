package com.cyj.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.demo.dao.ReadroomsDao;
import com.cyj.demo.entity.Readrooms;
import com.cyj.demo.service.ReadroomsService;

@Service
public class ReadroomsServiceImpl implements ReadroomsService {

	@Autowired
	private ReadroomsDao readroomsDao;

	@Override
	public List<Readrooms> getAllReadrooms(Readrooms rea) {
		return readroomsDao.getAllReadrooms(rea);
	}

	@Override
	public List<Readrooms> getAllReadroomsByIds() {
		return readroomsDao.getAllReadroomsByIds();
	}

	@Override
	public Integer getReadroomsCount(Readrooms rea) {
		return readroomsDao.getReadroomsCount(rea);
	}

	@Override
	public List<Readrooms> getByStringId(List<String> ids) {
		return readroomsDao.getByStringId(ids);
	}

}
