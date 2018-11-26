package com.yinzhan.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@MapperScan批量扫描
@MapperScan(value= {"com.yinzhan.demo.mapper"})
@EnableCaching
public class SpringBootWebJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebJspApplication.class, args);
	}
}
