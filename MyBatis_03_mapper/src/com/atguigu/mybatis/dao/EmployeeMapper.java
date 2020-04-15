package com.atguigu.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.atguigu.mybatis.bean.Employee;

public interface EmployeeMapper {

	//视频27 select返回map<id, Employee>
	//多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
	//@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
	@MapKey("lastName")
	public Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);
	// 视频27 select返回map<colomn, value>
	//返回一条记录的map；key就是列名，值就是对应的值
	public Map<String, Object> getEmpByIdReturnMap(Integer id);

	// 视频26 select返回list
	public List<Employee> getEmpsByLastNameLike(String lastName);

	// 视频21 参数传递,多个参数用map
	public Employee getEmpByMap(Map<String, Object> map);

	// 视频20 参数传递,多个参数用@Param
	public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
	
	public Employee getEmpById(Integer id);

	public Long addEmp(Employee employee);

	public boolean updateEmp(Employee employee);

	public void deleteEmpById(Integer id);
	
}
