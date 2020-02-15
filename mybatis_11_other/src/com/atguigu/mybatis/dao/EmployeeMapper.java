package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.bean.OraclePage;

import java.util.List;

public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);

	// 视频83, PageHelpler分页插件使用
	public List<Employee> getEmps();

	// 视频84 批量_BatchExecutor&Spring中配置批量sqlSession
	public Long addEmp(Employee employee);

	// 视频86 mybatis调用存储过程
	public void getPageByProcedure(OraclePage page);

	// 视频87 typeHandler_mybatis中枚举类型的默认处理和更改
	public Long addEmpForEnum(Employee employee);
}
