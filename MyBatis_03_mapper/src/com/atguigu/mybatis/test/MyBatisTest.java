package com.atguigu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.mybatis.bean.Department;
import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.DepartmentMapper;
import com.atguigu.mybatis.dao.EmployeeMapper;
import com.atguigu.mybatis.dao.EmployeeMapperAnnotation;
import com.atguigu.mybatis.dao.EmployeeMapperPlus;

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
		String resource = "mybatis-config.xml";
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

	@Test
	public void test01() throws IOException {
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现类对象
			//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmpById(1);
			System.out.println(mapper.getClass());
			System.out.println(employee);
		} finally {
			openSession.close();
		}

	}
	
	@Test
	public void test02() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
			Employee empById = mapper.getEmpById(1);
			System.out.println(empById);
		}finally{
			openSession.close();
		}
	}
	
	/**
	 * 视频 16 insert、update、delete元素
	 * 测试增删改
	 * 1、mybatis允许增删改直接定义以下类型返回值(返回值定义见接口 EmployeeMapper.java)
	 * 		Integer、Long、Boolean、void
	 * 2、我们需要手动提交数据
	 * 		sqlSessionFactory.openSession();===》手动提交
	 * 		sqlSessionFactory.openSession(true);===》自动提交
	 * @throws IOException 
	 */
	@Test
	public void test03() throws IOException{
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
		
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			//测试添加
//			Employee employee = new Employee(null, "jerry4",null, "1");
//			mapper.addEmp(employee);
			// 关于自增主键怎么获取,见 EmployeeMapper.xml内的addEmp
//			System.out.println(employee.getId());
			
			//测试修改
			Employee employee = new Employee(2, "Tom", "jerry@atguigu.com", "0");
			boolean updateEmp = mapper.updateEmp(employee);
			System.out.println(updateEmp);
			//测试删除
			//mapper.deleteEmpById(2);
			//2、手动提交数据
			openSession.commit();
		}finally{
			openSession.close();
		}
		
	}
	
	
	@Test
	public void test04() throws IOException{
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
		
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			// 视频20 参数传递,多个参数用@Param
//			Employee employee = mapper.getEmpByIdAndLastName(1, "tom");
			// 视频21 参数传递,多个参数用map
//			Map<String, Object> map = new HashMap<>();
//			map.put("id", 2);
//			map.put("lastName", "Tom");
//			map.put("tableName", "tbl_employee");// 视频24 参数处理 #与$的区别
//			Employee employee = mapper.getEmpByMap(map);
			
//			System.out.println(employee);
			// 视频26 select返回list
			List<Employee> like = mapper.getEmpsByLastNameLike("%e%");
			for (Employee employee : like) {
				System.out.println(employee);
			}
			// 视频27 select返回map<colomn, value>
			/*Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
			System.out.println(map);*/

			//视频27 select返回map<id, Employee>
			Map<String, Employee> map = mapper.getEmpByLastNameLikeReturnMap("%e%");
			System.out.println(map);
			
		}finally{
			openSession.close();
		}
	}
	
	@Test
	public void test05() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			// 视频28 resultMap自定义结果映射规则
			/*Employee empById = mapper.getEmpById(1);
			System.out.println(empById);*/

			// 视频30,31 关联查询,级联属性封装
//			Employee empAndDept = mapper.getEmpAndDept(1);
//			System.out.println(empAndDept);
//			System.out.println(empAndDept.getDept());

			// 视频32 使用association进行分步查询
//			Employee employee = mapper.getEmpByIdStep(3);
//			System.out.println(employee);
			//System.out.println(employee.getDept());
//			System.out.println(employee.getDept());

			// 视频37 discriminator鉴别器
			Employee employee = mapper.getEmpDisByIdStep(3);
			System.out.println(employee);
		}finally{
			openSession.close();
		}
		
		
	}
	
	@Test
	public void test06() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		
		try{
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			// 视频34 嵌套结果集,使用collection标签定义关联的集合类型的属性封装规则
			/*Department department = mapper.getDeptByIdPlus(1);
			System.out.println(department);
			System.out.println(department.getEmps());*/

			// 视频35 关联查询_collection分步查询&延迟加载
			Department deptByIdStep = mapper.getDeptByIdStep(1);
			System.out.println(deptByIdStep.getDepartmentName());
			// 查询emp会看到延迟加载的效果
			System.out.println(deptByIdStep.getEmps());
		}finally{
			openSession.close();
		}
	}	
	
}
