package com.cyj.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.demo.dao.ConsumelogsDao;
import com.cyj.demo.entity.Consumelogs;
import com.cyj.demo.service.ConsumelogsService;

@Service
public class ConsumelogsServiceImpl implements ConsumelogsService {

	@Autowired
	private ConsumelogsDao consumelogsDao;

	@Override
	public List<Consumelogs> getAllConsumelogs(Consumelogs con) {
		return consumelogsDao.getAllConsumelogs(con);
	}

	@Override
	public List<Consumelogs> getAllConsumelogsByIds() {
		return consumelogsDao.getAllConsumelogsByIds();
	}

	@Override
	public Integer getConsumelogsCount(Consumelogs con) {
		return consumelogsDao.getConsumelogsCount(con);
	}

	@Override
	public List<Consumelogs> getByStringId(List<String> ids) {
		return consumelogsDao.getByStringId(ids);
	}

	@Override
	public List<Consumelogs> getByCardNo(String consumelogs_cardNo) {
		return consumelogsDao.getByCardNo(consumelogs_cardNo);
	}

	@Override
	public boolean saveConsumelogs(Consumelogs con) {
		return consumelogsDao.saveConsumelogs(con) > 0 ? true : false;
	}

	@Override
	public boolean updConsumelogs(Consumelogs con) {
		return consumelogsDao.updConsumelogs(con) > 0 ? true : false;
	}

	@Override
	public List<Consumelogs> getByCount(int readId, String date) {
		return consumelogsDao.getByCount(readId, date);
	}

	@Override
	public List<Consumelogs> getHighCharts(Consumelogs con) {
		return consumelogsDao.getHighCharts(con);
	}

}
