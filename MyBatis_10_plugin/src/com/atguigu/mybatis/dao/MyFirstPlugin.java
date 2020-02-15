package com.atguigu.mybatis.dao;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * 视频80 插件编写&单个插件原理
 * 完成插件签名：
 *      type:告诉MyBatis当前插件用来拦截哪个对象
 * 		method:告诉MyBatis当前插件用来拦截哪个对象的哪个方法
 * 		args:告诉MyBatis当前插件用来拦截哪个对象的哪个方法的参数列表,因为存在重载的方法,就是通过args来分辨是哪个方法
 */
@Intercepts(
        {
                @Signature(type= StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
        })
public class MyFirstPlugin implements Interceptor {

    /**
     * intercept：拦截：
     * 		拦截目标对象的目标方法的执行；
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin...intercept:"+invocation.getMethod());
        //执行目标方法
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }

    /**
     * plugin：
     * 		包装目标对象的：包装：为目标对象创建一个代理对象
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin...plugin:mybatis将要包装的对象"+target);
        // 可以借助Plugin的wrap方法来使用当前Interceptor来包装我们目标对象
        Object wrap = Plugin.wrap(target, this);
        //返回为当前target创建的动态代理
        return wrap;
    }

    /**
     * setProperties：
     * 		将插件注册时 的property属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息："+properties);
    }
}
