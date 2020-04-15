package com.atguigu.mybatis.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private SqlSession sqlSession;
	
	public List<Employee> getEmps(){
		return employeeMapper.getEmps();
	}

	//视频84 批量_BatchExecutor&Spring中配置批量sqlSession
	public void addEmps() {
		//需要批量操作的话,
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			mapper.addEmp(new Employee(null,UUID.randomUUID().toString().substring(0, 5), "b", "1"));
		}
		long end = System.currentTimeMillis();
		//批量：（预编译sql一次==>设置参数===>10000次===>执行（1次））
		//Parameters: 616c1(String), b(String), 1(String)==>4598
		//非批量：（预编译sql=设置参数=执行）==》10000    10200
		System.out.println("执行时长："+(end-start));
	}
}
