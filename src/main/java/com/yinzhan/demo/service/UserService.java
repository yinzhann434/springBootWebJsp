package com.yinzhan.demo.service;

import com.yinzhan.demo.entity.UserTest;

public interface UserService {

	public UserTest getById(Long id);
	
	public UserTest inserUser(UserTest u);
}
