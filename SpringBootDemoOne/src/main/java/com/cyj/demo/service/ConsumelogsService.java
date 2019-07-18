package com.cyj.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.cyj.demo.entity.Consumelogs;

@Service
public interface ConsumelogsService {
	
	/**
	 * 查询所有资源统计表信息
	 * @return
	 */
	public List<Consumelogs> getAllConsumelogs(Consumelogs con);// 分页查询
	
	/**
	 * 查询所有资源统计表信息
	 * @return
	 */
	public List<Consumelogs> getAllConsumelogsByIds();
	
	/**
	 * 查询所有资源统计表总数
	 * @return
	 */
	public Integer getConsumelogsCount(Consumelogs con);
	
	/**
	 * 根据ids查询资源统计表
	 * @return
	 */
	public List<Consumelogs> getByStringId(List<String> ids);
	
	/**
	 * 查询 根据卡号查询
	 * @return
	 */
	public List<Consumelogs> getByCardNo(String consumelogs_cardNo);
	
	/**
	 * 添加资源统计表
	 * @return
	 */
	public boolean saveConsumelogs(Consumelogs con);
	
	/**
	 * 修改资源统计表
	 * @return
	 */
	public boolean updConsumelogs(Consumelogs con);
	
	/**
	 * 获取INT 求RoomId的总数
	 */
	public List<Consumelogs> getByCount(@Param("readRoomId") int readId, @Param("consumelogs_inTime")String date);
	
	/**
	 * 获取图表
	 */
	public List<Consumelogs> getHighCharts(Consumelogs con);
	
}
