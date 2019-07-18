package com.cyj.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.demo.entity.Memberships;
import com.cyj.demo.service.MembershipsService;
import com.cyj.demo.util.PoiUtils;

@RestController
@RequestMapping("/mem")
public class MembershipsController {
	
		@Autowired
		private MembershipsService membershipsService =null;
		
		private PoiUtils poiUtils = new PoiUtils();// 初始化工具类
		
		/**
		 * 检索查询科室表
		 * http://localhost:8080/SpringBootDemoOne/mem/getAllMembershipsByIds
		 * @param teachers
		 * @return
		 */
		@RequestMapping(value = "/getAllMembershipsByIds")
		public Object getAllMembershipsByIds(Memberships mem) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", membershipsService.getMembershipsCount(mem));
			map.put("rows", membershipsService.getAllMemberships(mem));
			return map;
		}
		
		/**
		 * 查询阅览室表
		 * http://localhost:8080/SpringBootDemoOne/mem/getAllMemberships
		 * @param teachers
		 * @return
		 */
		@RequestMapping(value = "/getAllMemberships")
		public Object getAllMemberships(Memberships mem) {
			return membershipsService.getAllMembershipsByIds();
		}
		
		/**
		 * 下载文件 http://localhost:8080/SpringBootDemoOne/mem/downloadFile?id=1,2,3
		 * @throws FileNotFoundException 
		 * 
		 * @throws IOException
		 */
		@RequestMapping("/downloadFile")
		public Object downloadFile(String id, HttpServletResponse response)  {// 文件默认下载根路径下的/download目录下
			String downloadName = "导出的阅览室数据.xlsx";// 相对当前项目的路径
			
			List<String> lists = new ArrayList<String>();
			String[] ids = id.split(",");
			for (String dids : ids) {
				lists.add(dids);
			}
			List<Memberships> list = membershipsService.getByStringId(lists);// 要导出的数据集合
			try {
				response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
				response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
				response.setHeader("Content-disposition", "attachment;filename="
						+ new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
				ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流

				poiUtils.createExcel(list, Memberships.class, outputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
}
