package com.atguigu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;


import com.atguigu.mybatis.bean.EmpStatus;
import com.atguigu.mybatis.bean.OraclePage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import org.junit.Test;

/**
 * 1、接口式编程
 * 	原生：		Dao		====>  DaoImpl
 * 	mybatis：	Mapper	====>  xxMapper.xml
 * 
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 		（将接口和xml进行绑定）
 * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * 		sql映射文件：保存了每一个sql语句的映射信息：
 * 					将sql抽取出来。
 * 
 * 
 * @author lfy
 *
 */
public class MyBatisTest {
	

	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "conf/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	/**
	 * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
	 * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。 
	 * 3、将sql映射文件注册在全局配置文件中
	 * 4、写代码：
	 * 		1）、根据全局配置文件得到SqlSessionFactory；
	 * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
	 * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
	 * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {

		// 2、获取sqlSession实例，能直接执行已经映射的sql语句
		// sql的唯一标识：statement Unique identifier matching the statement to use.
		// 执行sql要用的参数：parameter A parameter object to pass to the statement.
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			Employee employee = openSession.selectOne(
					"com.atguigu.mybatis.dao.EmployeeMapper.getEmpById", 1);
			System.out.println(employee);
		} finally {
			openSession.close();
		}

	}

	// 视频83, PageHelpler分页插件使用
	@Test
	public void test01() throws IOException {
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Page<Object> page = PageHelper.startPage(1, 5);

			List<Employee> emps = mapper.getEmps();
			//传入要连续显示多少页
			PageInfo<Employee> info = new PageInfo<>(emps, 5);
			for (Employee employee : emps) {
				System.out.println(employee);
			}

			System.out.println("当前页码："+page.getPageNum());
			System.out.println("总记录数："+page.getTotal());
			System.out.println("每页的记录数："+page.getPageSize());
			System.out.println("总页码："+page.getPages());
			///xxx
			// PageInfo可以提供更详细的分页信息
			System.out.println("当前页码："+info.getPageNum());
			System.out.println("总记录数："+info.getTotal());
			System.out.println("每页的记录数："+info.getPageSize());
			System.out.println("总页码："+info.getPages());
			System.out.println("是否第一页："+info.isIsFirstPage());
			System.out.println("连续显示的页码：");
			int[] nums = info.getNavigatepageNums();
			for (int i = 0; i < nums.length; i++) {
				System.out.println(nums[i]);
			}


			//xxxx
		} finally {
			openSession.close();
		}

	}


	// 视频84 批量_BatchExecutor&Spring中配置批量sqlSession
	@Test
	public void testBatch() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

		//可以执行批量操作的sqlSession,以前的批量插入,需要批量执行预编译等,很耗性能,BATCH专用于批量操作的SqlSession
		SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		long start = System.currentTimeMillis();
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			for (int i = 0; i < 10000; i++) {
				mapper.addEmp(new Employee(UUID.randomUUID().toString().substring(0, 5), "b", "1"));
			}
			openSession.commit();
			long end = System.currentTimeMillis();
			//批量：（预编译sql一次==>设置参数===>10000次===>执行（1次））
			//Parameters: 616c1(String), b(String), 1(String)==>4598
			//非批量：（预编译sql=设置参数=执行）==》10000    10200
			System.out.println("执行时长："+(end-start));
		}finally{
			openSession.close();
		}

	}

	/**
	 * 视频86 mybatis调用存储过程
	 * oracle分页：
	 * 		借助rownum：行号；子查询；
	 * 存储过程包装分页逻辑
	 * @throws IOException
	 */
	@Test
	public void testProcedure() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			OraclePage page = new OraclePage();
			page.setStart(1);
			page.setEnd(5);
			mapper.getPageByProcedure(page);

			System.out.println("总记录数："+page.getCount());
			System.out.println("查出的数据："+page.getEmps().size());
			System.out.println("查出的数据："+page.getEmps());
		}finally{
			openSession.close();
		}
	}

	@Test
	public void testEnumUse(){
		EmpStatus login = EmpStatus.LOGIN;
		System.out.println("枚举的索引："+login.ordinal());
		System.out.println("枚举的名字："+login.name());

		System.out.println("枚举的状态码："+login.getCode());
		System.out.println("枚举的提示消息："+login.getMsg());
	}

	/**
	 * 视频87 typeHandler_mybatis中枚举类型的默认处理和更改
	 * 视频88 使用自定义的类型处理器处理枚举类型
	 * 默认mybatis在处理枚举对象的时候保存的是枚举的名字：EnumTypeHandler
	 * 改变使用：EnumOrdinalTypeHandler：保存枚举的索引
	 * @throws IOException
	 */
	@Test
	public void testEnum() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
//			Employee employee = new Employee("test_enum", "enum@atguigu.com","1");
//			mapper.addEmpForEnum(employee);
//			System.out.println("保存成功"+employee.getId());
//			openSession.commit();
			// 这里要去查库,看get方法返回来的总是Enum的value
			Employee empById = mapper.getEmpById(31010);
			System.out.println(empById.getEmpStatus());
		}finally{
			openSession.close();
		}
	}
}
