package com.cyj.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.demo.dao.SectionsDao;
import com.cyj.demo.entity.Sections;
import com.cyj.demo.service.SectionsService;

@Service
public class SectionsServiceImpl implements SectionsService {
	
	@Autowired
	private SectionsDao SectionsDao;
	
	@Override
	public boolean saveSections(Sections sec) {
		return SectionsDao.saveSections(sec) > 0 ? true:false;
	}

	@Override
	public boolean delSections(List<String> ids) {
		return SectionsDao.delSections(ids) > 0 ? true:false;
	}

	@Override
	public boolean updteSections(Sections sec) {
		return SectionsDao.updteSections(sec) > 0 ? true:false;
	}

	@Override
	public List<Sections> getAllSections(Sections sec) {
		return SectionsDao.getAllSections(sec);
	}
	
	@Override
	public List<Sections> getAllSectionsByIds() {
		return SectionsDao.getAllSectionsByIds();
	}

	@Override
	public Integer getSectionsCount(Sections sec) {
		return SectionsDao.getSectionsCount(sec);
	}

	@Override
	public List<Sections> getByStringId(List<String> ids) {
		return SectionsDao.getByStringId(ids);
	}

}
