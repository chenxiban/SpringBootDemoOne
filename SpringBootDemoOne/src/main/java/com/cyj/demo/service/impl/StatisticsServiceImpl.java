package com.cyj.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.demo.dao.StatisticsDao;
import com.cyj.demo.entity.Statistics;
import com.cyj.demo.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private StatisticsDao statisticsDao;

	@Override
	public boolean saveStatistics(Statistics sta) {
		return statisticsDao.saveStatistics(sta)>0 ? true:false;
	}

	@Override
	public List<Statistics> getAllStatistics(Statistics sta) {
		return statisticsDao.getAllStatistics(sta);
	}

	@Override
	public Integer getStatisticsCount(Statistics sta) {
		return statisticsDao.getStatisticsCount(sta);
	}

	@Override
	public List<Statistics> getByStringId(List<String> ids) {
		return statisticsDao.getByStringId(ids);
	}

	@Override
	public List<Statistics> getHighCharts(Statistics sta) {
		return statisticsDao.getHighCharts(sta);
	}
	
}
