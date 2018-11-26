package com.yinzhan.demo.mapper;

import com.yinzhan.demo.entity.UserTest;

public interface UserMapper {

	public UserTest getById(Long id);
	
	public int inserUser(UserTest u);
}
