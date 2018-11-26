package com.yinzhan.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yinzhan.demo.entity.Dept;
/**
 * 
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  DeptMapper.java   
 * @Package com.yinzhan.demo.mapper   
 * @Description:    注解版mybatis  
 *       每一个类都加@Mapper麻烦  在springboot主配之类加@MapperScan
 * @author: 阴展  
 * @date:   2018年10月16日 下午3:49:19   
 * @version V1.0 
 * @Copyright: 2018  yinzhan@Pactera.com
 */
//@Mapper
public interface DeptMapper {

	@Select("select *  from dept where id = #{id}")
	public Dept getById(Long id);
	
	@Delete("delete from dept where id = #{id}")
	public int deleById(Long id);
	
	//返回自增主键
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into dept(dept_name) values (#{deptName}) ")
	public int insertDept(Dept d);
	
	@Update("update dept set dept_name = #{deptName} where id = #{id}")
	public int updateDept(Dept d);
}
