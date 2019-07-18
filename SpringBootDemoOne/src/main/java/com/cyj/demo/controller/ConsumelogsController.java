package com.cyj.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.demo.entity.Consumelogs;
import com.cyj.demo.service.ConsumelogsService;
import com.cyj.demo.util.PoiUtils;

@RestController
@RequestMapping("/con")
public class ConsumelogsController {

	@Autowired
	private ConsumelogsService consumelogsService = null;

	private static Calendar now = Calendar.getInstance();

	private PoiUtils poiUtils = new PoiUtils();// 初始化工具类

	/**
	 * 检索查询科室表 http://localhost:8080/SpringBootDemoOne/con/getAllConsumelogsByIds
	 * 
	 * @param teachers
	 * @return
	 */
	@RequestMapping(value = "/getAllConsumelogsByIds")
	public Object getAllConsumelogsByIds(Consumelogs mem) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", consumelogsService.getConsumelogsCount(mem));
		map.put("rows", consumelogsService.getAllConsumelogs(mem));
		return map;
	}

	/**
	 * 查询阅览室表 http://localhost:8080/SpringBootDemoOne/con/getAllConsumelogs
	 * 
	 * @param teachers
	 * @return
	 */
	@RequestMapping(value = "/getAllConsumelogs")
	public Object getAllConsumelogs(Consumelogs mem) {
		return consumelogsService.getAllConsumelogsByIds();
	}

	/**
	 * 下载文件 http://localhost:8080/SpringBootDemoOne/con/downloadFile?id=1,2,3
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/downloadFile")
	public Object downloadFile(String id, HttpServletResponse response) {// 文件默认下载根路径下的/download目录下
		String downloadName = "导出的阅览室数据.xlsx";// 相对当前项目的路径

		List<String> lists = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			lists.add(dids);
		}
		List<Consumelogs> list = consumelogsService.getByStringId(lists);// 要导出的数据集合
		try {
			response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
			response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
			response.setHeader("Content-disposition",
					"attachment;filename=" + new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
			ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流

			poiUtils.createExcel(list, Consumelogs.class, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载文件 http://localhost:8080/SpringBootDemoOne/con/downloadFileAll
	 * 
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/downloadFileAll")
	public Object downloadFileAll(HttpServletResponse response) throws InterruptedException {// 文件默认下载根路径下的/download目录下
		String downloadName = "导出的所有阅览室数据.xlsx";// 相对当前项目的路径

		// 内存中只创建100个对象，写临时文件，当超过100条，就将内存中不用的对象释放。
		Workbook wb = new SXSSFWorkbook(100); // 关键语句
		Sheet sheet = null; // 工作表对象
		Row nRow = null; // 行对象
		Cell nCell = null; // 列对象

		List<Consumelogs> list = consumelogsService.getAllConsumelogsByIds();// 要导出的数据集合

		long startTime = System.currentTimeMillis(); // 开始时间
		System.out.println("strat execute time: " + startTime);

		int rowNo = 0; // 总行号
		int pageRowNo = 0; // 页行号
		
		if (list.size() > 0 && list != null) {
			// 打印10000条后切换到下个工作表，可根据需要进行拓展，
			// 1百万,2百万，3百万...数据一样操作，只要不超过1048576就可以
			if (rowNo % 100000 == 0) {
				System.out.println("Current Sheet:" + rowNo / 100000);
				sheet = wb.createSheet("我的第" + (rowNo / 100000) + "个工作簿");// 建立新的sheet对象
				sheet = wb.getSheetAt(rowNo / 100000); // 动态指定当前的工作表
				pageRowNo = 0; // 每当新建了工作表就将当前工作表的行号重置为0
			}
			rowNo++;
			nRow = sheet.createRow(pageRowNo++); // 新建行对象

			// 打印每行，每行有6列数据 list.size()=XXX.getColumnCount()==6 --- 列属性的个数
			for (int j = 0; j < list.size(); j++) {
				nCell = nRow.createCell(j);
				nCell.setCellValue((RichTextString) list.get(j + 1));
			}

			if (rowNo % 10000 == 0) {
				System.out.println("row no: " + rowNo);
			}
			Thread.sleep(1000); // 休息一下，防止对CPU占用
		}

		long finishedTime = System.currentTimeMillis(); // 处理完成时间
		System.out.println("finished execute  time: " + (finishedTime - startTime) / 1000 + "m");

		try {
			response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
			response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
			response.setHeader("Content-disposition",
					"attachment;filename=" + new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
			ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流

			//poiUtils.createExcel(list, Consumelogs.class, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示图表 http://localhost:8080/SpringBootDemoOne/con/getHighChartsData?years=
	 * 2018&month=10&day=8
	 */
	@RequestMapping("/getHighChartsData")
	public Object getHighChartsData(Consumelogs con) {
		int year = now.get(Calendar.YEAR);// 获取今天的年
		int months = (now.get(Calendar.MONTH) + 1);// 获取今天的月

		String monthstr = "";
		// 处理月为单数时
		if (months < 10) {
			monthstr += "0" + months;
		} else {
			monthstr += months;
		}

		// 定义查询需要使用的date日期格式
		System.out.println("今天的年为:" + year + ",今天的月为:" + months);
		String data = year + "-" + monthstr;
		System.out.println(data);

		String dateString = null;
		if (con.getYears() != null && con.getYears() != "" && con.getMonth() != null && con.getMonth() != "") {
			dateString = con.getYears() + "-" + con.getMonth();
			con.setTests(con.getTests());
			con.setDateString(dateString);
			return consumelogsService.getHighCharts(con);
		} else {
			con.setTests(con.getTests());
			con.setDateString(dateString);
			con.setData(data);
			return consumelogsService.getHighCharts(con);
		}

	}

}
