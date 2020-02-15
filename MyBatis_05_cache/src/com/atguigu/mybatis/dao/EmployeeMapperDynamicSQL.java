package com.atguigu.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.mybatis.bean.Employee;

public interface EmployeeMapperDynamicSQL {

	// 视频48 动态sql_内置参数_parameter&_databaseId
	public List<Employee> getEmpsTestInnerParameter(Employee employee);

	//视频39 动态sql_if_判断&OGNL
	//携带了哪个字段查询条件就带上这个字段的值
	public List<Employee> getEmpsByConditionIf(Employee employee);

	// 视频41 动态sql_trim_自定义字符串截取
	public List<Employee> getEmpsByConditionTrim(Employee employee);

	// 视频42 动态sql_choose_分支选择
	public List<Employee> getEmpsByConditionChoose(Employee employee);

	// 视频43 动态sql_set_与if结合的动态更新,或者用trim
	public void updateEmp(Employee employee);

	// 视频44 动态sql_foreach_遍历集合
	//查询员工id'在给定集合中的
	public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

	// 视频45 动态sql_foreach_mysql下foreach批量插入的两种方式
	// 视频46 动态sql_foreach_oracle下批量插入的两种方式
	public void addEmps(@Param("emps") List<Employee> emps);

}
