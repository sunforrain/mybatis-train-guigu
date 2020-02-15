package com.atguigu.mybatis.dao;

import java.util.List;

import com.atguigu.mybatis.bean.Employee;

public interface EmployeeMapperPlus {

	// 视频28 resultMap自定义结果映射规则
	public Employee getEmpById(Integer id);

	// 视频30,31 关联查询,级联属性封装
	public Employee getEmpAndDept(Integer id);

	// 视频32 使用association进行分步查询
	public Employee getEmpByIdStep(Integer id);

	// 视频35 关联查询_collection分步查询&延迟加载
	public List<Employee> getEmpsByDeptId(Integer deptId);

	// 视频37 discriminator鉴别器
	public Employee getEmpDisByIdStep(Integer id);
}
