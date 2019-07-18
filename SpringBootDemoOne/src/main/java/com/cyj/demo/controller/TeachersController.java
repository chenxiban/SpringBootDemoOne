package com.cyj.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cyj.demo.entity.Teachers;
import com.cyj.demo.service.TeachersService;
import com.cyj.demo.util.MyPoiHelp;
import com.cyj.demo.util.PoiUtils;

@RestController
@RequestMapping("/tea")
//@CrossOrigin // 该注解用于解决跨域
public class TeachersController {

	@Autowired
	private TeachersService teachersService = null;

	private PoiUtils poiUtils = new PoiUtils();// 初始化工具类

	/**
	 * 分页检索查询老师表 http://localhost:8080/SpringBootDemoOne/tea/getAllTeachers
	 * 
	 * @param teachers
	 * @return
	 */
	@RequestMapping(value = "/getAllTeachers")
	public Object getAllTeachers(@ModelAttribute Teachers tea) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", teachersService.getTeachersCount(tea));
		map.put("rows", teachersService.getAllTeachers(tea));
		return map;
	}

	/**
	 * 批量删除老师信息 http://localhost:8080/SpringBootDemoOne/tea/delTeachers
	 * 
	 * @param tea
	 * @return
	 */
	@RequestMapping(value = "/delTeachers")
	public Object delTeachers(String id) {
		List<String> list = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(list);
		if (teachersService.delTeachers(list)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 添加老师表信息 http://localhost:8080/SpringBootDemoOne/tea/addTeachers
	 * 
	 * @param teachers
	 * @return
	 */
	@RequestMapping(value = "/addTeachers")
	public Object addTeachers(@ModelAttribute Teachers tea) {
		if (teachersService.saveTeachers(tea)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 修改老师表信息 http://localhost:8080/SpringBootDemoOne/tea/updTeachers
	 * 
	 * @param teachers
	 * @return
	 */
	@RequestMapping(value = "/updTeachers")
	public Object updTeachers(@ModelAttribute Teachers tea) {
		if (teachersService.updteTeachers(tea)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Excel导入数据 http://localhost:8080/SpringBootDemoOne/tea/upload SpringMVC处理文件上传
	 * 单文件上传
	 * 
	 * @param myfile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload")
	public Object upload(@RequestParam(value = "myfile", required = false) MultipartFile myfile,
			@RequestParam(value = "desc", required = false) String desc,
			@RequestParam(value = "id", required = false) String id) {
		String name = myfile.getName();// form表单中参数名称
		String originalFilename = myfile.getOriginalFilename();// 得到上传文件的名称
		System.out.println("表单中文件参数名称 name=>" + name);
		System.out.println("上传的文件原始名称 originalfileName=>" + originalFilename);
		System.out.println("文件描述信息 desc=>" + desc);

		int ids = Integer.parseInt(id);
		List<Teachers> list = null;
		try {
			list = (List<Teachers>) poiUtils.parseExcel(Teachers.class,
					myfile.getInputStream(), originalFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Boolean boo = null;
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSectionID(ids);
			list.get(i).setTeachers_status(0);
			boo=teachersService.saveTeachers(list.get(i));
		}
		return boo;
	}

	/**
	 * 下载文件 http://localhost:8080/SpringBootDemoOne/tea/downloadFile?id=1,2,3
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/downloadFile")
	public Object downloadFile(String id, HttpServletResponse response) {// 文件默认下载根路径下的/download目录下
		String downloadName = "导出的老师数据.xlsx";// 相对当前项目的路径

		List<String> lists = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			lists.add(dids);
		}
		List<Teachers> list = teachersService.getByStringId(lists);// 要导出的数据集合
		try {
			response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
			response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
			response.setHeader("Content-disposition",
					"attachment;filename=" + new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
			ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流

			poiUtils.createExcel(list, Teachers.class, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 下载老师模板文件 
	 * http://localhost:8080/SpringBootDemoOne/tea/downloadFileTea
	 * @throws IOException
	 */
	@RequestMapping("/downloadFileTea")
	public Object downloadFileTea(HttpServletResponse response) throws IOException  {// 文件默认下载根路径下的/download目录下
		String downloadName = "导出老师Excle模板.xlsx";// 相对当前项目的路径
		
		response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
		response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
		ServletOutputStream os = response.getOutputStream();// 获取响应的字节输出流
		//FileOutputStream os = new FileOutputStream(downloadName);
		Workbook workBook = null;
		// 创建一个Excel文件
		try {
			workBook = new HSSFWorkbook();// 构造一个xls后缀的EXCEL文件对象,2007
		} catch (Exception e) {
			e.printStackTrace();
			workBook = new XSSFWorkbook();// 构造一个xlsx后缀的EXCEL文件对象,2010
		}
		Sheet sheet = workBook.createSheet("teachersData");
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth(30);
		try {
			int i = 0;
			Row row1 = sheet.createRow(i++);// 创建表头行
			row1.setHeightInPoints((short) 18);// 设置表头行高度
			Cell cell = row1.createCell(0);
			cell.setCellType(CellType.STRING);// 设置单元格类型为文本
			cell.setCellStyle(MyPoiHelp.defaultHeaderCellStyle(workBook));// 设置表头默认样式
			cell.setCellStyle(MyPoiHelp.defaultCellStyle(workBook));// 设置表头默认样式
			cell.setCellValue("卡号(字符串类型默认长度20)");
			cell = row1.createCell(1);
			cell.setCellValue("名字(字符串类型默认长度20)");
			cell = row1.createCell(2);
			cell.setCellValue("性别(枚举类型只允许:男,女)");
			cell = row1.createCell(3);
			cell.setCellValue("备注(字符串类型默认长度2000)");
			cell = row1.createCell(4);
			workBook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
