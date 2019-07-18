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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cyj.demo.entity.Sections;
import com.cyj.demo.service.SectionsService;
import com.cyj.demo.util.MyPoiHelp;
import com.cyj.demo.util.PoiUtils;

@RestController
@RequestMapping("/sec")
public class SectionsController {
	
	@Autowired
	private SectionsService sectionsService =null;
	
	private PoiUtils poiUtils = new PoiUtils();// 初始化工具类
	
	/**
	 * 检索查询科室表
	 * http://localhost:8080/SpringBootDemoOne/sec/getAllSectionsByIds
	 * @param teachers
	 * @return
	 */
	@RequestMapping(value = "/getAllSectionsByIds")
	public Object getAllSectionsByIds(Sections sec) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", sectionsService.getSectionsCount(sec));
		map.put("rows", sectionsService.getAllSections(sec));
		return map;
	}
	
	/**
	 * 查询科室表
	 * http://localhost:8080/SpringBootDemoOne/sec/getAllSections
	 * @param teachers
	 * @return
	 */
	@RequestMapping(value = "/getAllSections")
	public Object getAllSections(Sections sec) {
		return sectionsService.getAllSectionsByIds();
	}
	
	/**
	 * Excel导入数据 http://localhost:8080/SpringBootDemoOne/sec/upload SpringMVC处理文件上传
	 * 单文件上传
	 * 
	 * @param myfile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload", produces = "text/html;charset=UTF-8", name = "上传文件")
	public Object upload(@RequestParam(value = "myfile", required = false) MultipartFile myfile,
			@RequestParam(value = "desc", required = false) String desc) {
		String name = myfile.getName();// form表单中参数名称
		String originalFilename = myfile.getOriginalFilename();// 得到上传文件的名称
		System.out.println("表单中文件参数名称 name=>" + name);
		System.out.println("上传的文件原始名称 originalfileName=>" + originalFilename);
		System.out.println("文件描述信息 desc=>" + desc);

		List<Sections> list = null;
		try {
			list = (List<Sections>) poiUtils.parseExcel(Sections.class,
					myfile.getInputStream(), originalFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Boolean boo = null;
		for (int i = 0; i < list.size(); i++) {
			boo=sectionsService.saveSections(list.get(i));
		}
		return boo;
	}
	
	/**
	 * 下载文件 http://localhost:8080/SpringBootDemoOne/sec/downloadFile?id=1,2,3
	 * @throws FileNotFoundException 
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/downloadFile")
	public Object downloadFile(String id, HttpServletResponse response)  {// 文件默认下载根路径下的/download目录下
		String downloadName = "导出的科室数据.xlsx";// 相对当前项目的路径
		
		List<String> lists = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			lists.add(dids);
		}
		List<Sections> list = sectionsService.getByStringId(lists);// 要导出的数据集合
		try {
			response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
			response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
			ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流

			poiUtils.createExcel(list, Sections.class, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 下载科室模板文件 
	 * http://localhost:8080/SpringBootDemoOne/sec/downloadFileSec
	 * @throws IOException
	 */
	@RequestMapping("/downloadFileSec")
	public Object downloadFileSec(HttpServletResponse response) throws IOException  {// 文件默认下载根路径下的/download目录下
		String downloadName = "导出科室Excle模板.xlsx";// 相对当前项目的路径
		//HttpHeaders headers=new HttpHeaders();
		// 通知浏览器以attachment下载方式,打开文件
		//headers.setContentDispositionFormData("attachment", downloadName);
		// 二进制数据流
		//headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
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
		Sheet sheet = workBook.createSheet("sectionsData");
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth(25);
		try {
			int i = 0;
			Row row1 = sheet.createRow(i++);// 创建表头行
			row1.setHeightInPoints((short) 18);// 设置表头行高度
			Cell cell = row1.createCell(0);
			cell.setCellType(CellType.STRING);// 设置单元格类型为文本
			cell.setCellStyle(MyPoiHelp.defaultHeaderCellStyle(workBook));// 设置表头默认样式
			cell.setCellStyle(MyPoiHelp.defaultCellStyle(workBook));// 设置表头默认样式
			cell.setCellValue("科室名称(字符串类型默认长度50)");
			cell = row1.createCell(1);
			cell.setCellValue("科室备注(字符串类型默认长度2000)");
			cell = row1.createCell(2);
			workBook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
