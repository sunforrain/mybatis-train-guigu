package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Department;

public interface DepartmentMapper {
	
	public Department getDeptById(Integer id);

	// 视频34 嵌套结果集,使用collection标签定义关联的集合类型的属性封装规则
	public Department getDeptByIdPlus(Integer id);

	// 视频35 关联查询_collection分步查询&延迟加载
	public Department getDeptByIdStep(Integer id);
}
