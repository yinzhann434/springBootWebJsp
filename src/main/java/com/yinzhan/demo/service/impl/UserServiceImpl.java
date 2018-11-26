package com.yinzhan.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yinzhan.demo.entity.UserTest;
import com.yinzhan.demo.mapper.UserMapper;
import com.yinzhan.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	
	/**
	 * 将方法的运行结果进行缓存，以后要相同的结果从缓存取，不需要调用方法
	 *    CacheManager：指定缓存管理器，管理多个cache缓存组件,对缓存的CRUD操作是再cache中，每个缓存组建有自己唯一的名字
	 *    value/cacheNames  指定缓存组件的名字，二选一
	 *    key:缓存数据使用的key，默认使用方法的参数值，#id参数值=#a0=#p0= #root.args[0]
	 *    keyGenerator:key生成器：可以自己指定key生成器的组件id
	 *    key/keyGenerator 二选一
	 *    condition：指定符合条件进行缓存  condition="#id>0" 
	 *    unless：否定缓存：当unless的条件成立，方法的返回值就不缓存，可以获取到结果进行判断
	 *            unless="#result == null"
	 *    sync：异步
	 *    
	 * 运行：
	 *    1.  方法运行之前   ，先去查cache,按照cacheNames指定的名字获取
	 *    2.  在cache中查找缓存内容
	 *    2.1  使用一个key，默认就是方法的名字
	 *        key按照keyGenerator规则生成  默认使用SimpleKeyGenerator
	 *         SimpleKeyGenerator：key的默认策略  没有参数：new SimpleKey()  有一个参数  就使用参数  多个参数 new SimpleKey(params)
	 *    2.2  没有查找到内容，就调用目标方法
	 *    2.3 将目标方法返回的结果放入缓存
	 */   
	@Cacheable(cacheNames="{emps}")
	@Override
	public UserTest getById(Long id) {
		return userMapper.getById(id);
	}

	@Override
	public UserTest inserUser(UserTest u) {
		userMapper.inserUser(u);
		return u;
	}

}
