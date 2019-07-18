package com.cyj.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot 应用启动类
 * @author 
 * 陈永佳  2018-10-18 16:40
 */

/**
 * 1).@SpringBootApplication标注启动配置入口，
 * run()方法会创建一个Spring应用上下文(Application Context)。
 * SpringBoot通过启动内嵌的Servlet容器（默认tomcat）用来处理Http请求。
 * 2).@RestController是特殊的Controller，他的返回值直接作为Http Response的Body部分返回给浏览器
 * 3).Spring WebMvc框架会将Servlet容器里收到的Http请求根据路径分发到对应的@Controller下进行处理。
 */
//@ComponentScan
@EnableAsync// 启动异步处理
@SpringBootApplication// SpringBoot 应用标识
@EnableTransactionManagement// 启注解事务管理,等同于xml配置方式的<tx:annotation-driven />
@MapperScan("com.cyj.demo.dao")// 指定扫描的mapper接口所在的包
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
}
