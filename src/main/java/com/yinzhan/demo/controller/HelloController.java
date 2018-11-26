package com.yinzhan.demo.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhan.demo.entity.Dept;
import com.yinzhan.demo.entity.UserTest;
import com.yinzhan.demo.mapper.DeptMapper;
import com.yinzhan.demo.mapper.UserMapper;
import com.yinzhan.demo.service.UserService;

@Controller
public class HelloController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DeptMapper DeptMapper;
	@Autowired
	UserService userService;
	
	@RequestMapping("hello")
	public String hello(Model m) {
		m.addAttribute("msg", "iam hot_fix...222");
		return "hello";
	}
	
	@RequestMapping("users")
	@ResponseBody
	public List<Map<String, Object>> users() {
		return jdbcTemplate.queryForList("select *  from user");
	}
	
	@RequestMapping("/dept/{id}")
	@ResponseBody
	public Dept getDept(@PathVariable("id") Long id) {
		return DeptMapper.getById(id);
	}
	
	@RequestMapping("/insertDept")
	@ResponseBody
	public Dept insertDept(Dept d) {
		DeptMapper.insertDept(d);
		return d;
	}
	
	@RequestMapping("/user/{id}")
	@ResponseBody
	public UserTest getuser(@PathVariable("id") Long id) {
		return userService.getById(id);
	}
	
	@RequestMapping("/insertUser")
	@ResponseBody
	public UserTest insertUser(UserTest u) {
		return userService.inserUser(u);
	}
}
