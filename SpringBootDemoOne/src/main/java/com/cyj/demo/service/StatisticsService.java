package com.cyj.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyj.demo.entity.Statistics;

@Service
public interface StatisticsService {
	
	/**
	 * 查询所有日统计表信息
	 * @return
	 */
	public List<Statistics> getAllStatistics(Statistics sta);// 分页查询
	
	/**
	 * 查询所有日统计表总数
	 * @return
	 */
	public Integer getStatisticsCount(Statistics sta);
	
	/**
	 * 根据String ids查询日统计信息
	 * @param ids
	 * @return
	 */
	public List<Statistics> getByStringId(List<String> ids);
	
	/**
	 * 添加日统计表
	 * @return
	 */
	public boolean saveStatistics(Statistics sta);
	
	/**
	 * 显示图表
	 * @param sta
	 * @return
	 */
	public List<Statistics> getHighCharts(Statistics sta);
	
}
