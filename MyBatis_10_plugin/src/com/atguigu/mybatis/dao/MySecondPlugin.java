package com.atguigu.mybatis.dao;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

// 视频81 多个插件运行流程
@Intercepts(
		{
			@Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
		})
public class MySecondPlugin implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("MySecondPlugin...intercept:"+invocation.getMethod());
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		System.out.println("MySecondPlugin...plugin:"+target);
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

}