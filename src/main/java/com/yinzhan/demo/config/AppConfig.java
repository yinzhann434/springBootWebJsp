package com.yinzhan.demo.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


@Configuration
public class AppConfig {

	/**
	 * 
	 * @Title: dataSource 
	 * @author: 阴展  yinzhan@Pactera.com
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @date:  使用自定义阿里数据源  绑定自定义配置
	 * @param: @return      
	 * @return: DataSource      
	 * @throws
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return new DruidDataSource();
	}
	
	//配置Druid数据监控
	//1.配置一个管理后台servlet  /druid/*
	@Bean
	public ServletRegistrationBean<StatViewServlet> statViewServlet(){
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<StatViewServlet>();
		bean.setServlet(new StatViewServlet());
		bean.setUrlMappings(Arrays.asList("/druid/*"));
		Map<String, String> initParameters = new HashMap<>();
		//后台登陆用户名密码
		initParameters.put("loginUsername", "admin");
		initParameters.put("loginPassword", "123456");
		//允许访问 默认所有
		initParameters.put("allow", "");
		//拒绝
		initParameters.put("deny", "");
		bean.setInitParameters(initParameters);
		return bean;
	}
	
	//2.配置一个监控的webFilter
	@Bean
	public FilterRegistrationBean<WebStatFilter> webStatFilter(){
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>();
		bean.setFilter(new WebStatFilter());
		bean.setUrlPatterns(Arrays.asList("/*"));
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("exclusions", "*.js,*.css,/druid/*");
		bean.setInitParameters(initParameters);
		return bean;
	}
	
	//mybatisConfiguration--定制器--开启数据库驼峰命名
	@Bean
	public ConfigurationCustomizer  configurationCustomizer() {
		return new ConfigurationCustomizer() {
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
				configuration.setMapUnderscoreToCamelCase(true);
			}
		};
	}
}
