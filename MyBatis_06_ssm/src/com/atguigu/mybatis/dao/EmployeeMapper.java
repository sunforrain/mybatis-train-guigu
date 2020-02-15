package com.atguigu.mybatis.dao;


import java.util.List;

import com.atguigu.mybatis.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);
	
	public List<Employee> getEmps();

	// 视频84 批量_BatchExecutor&Spring中配置批量sqlSession
	public Long addEmp(Employee employee);

}
