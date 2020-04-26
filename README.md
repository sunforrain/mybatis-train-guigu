# MyBatis-train
myBatis的代码,仅供练习
# 目录        
### 1 : MyBatis_简介, 见课件
### 2 : MyBatis_下载, 见课件
### mybatis_01_helloworld
#### 3 : MyBatis_HelloWorld
#### 4 : MyBatis_接口式编程
#### 5 : MyBatis_小结(1)
#### 6 : MyBatis_全局配置文件_引入dtd约束, 见课件
#### 7 : MyBatis_全局配置文件_properties_引入外部配置文件, 见课件
#### 8 : MyBatis_全局配置文件_settings_运行时行为设置
### mybatis_02_config
#### 9 : MyBatis_全局配置文件_typeAliases_别名
#### 10 : MyBatis_全局配置文件_typeHandlers_类型处理器简介, 见课件
#### 11 : MyBatis_全局配置文件_plugins_插件简介, 后面讲
#### 12 : MyBatis_全局配置文件_enviroments_运行环境
#### 13 : MyBatis_全局配置文件_databaseIdProvider_多数据库支持
#### 14 : MyBatis_全局配置文件_mappers_sql映射注册
#### 15 : MyBatis_小结(2)
### MyBatis_03_mapper
#### 16 : MyBatis_映射文件_增删改查, 见课件
#### 17 : MyBatis_映射文件_insert_获取自增主键的值
#### 18 : MyBatis_映射文件_insert_Oracle使用序列生成主键演示
#### 19 : MyBatis_映射文件_insert_获取非自增主键的值_selectKey
#### 20 : MyBatis_映射文件_参数处理_单个参数&amp;amp;多个参数&amp;amp;命名参数
#### 21 : MyBatis_映射文件_参数处理_POJO&amp;amp;Map&amp;amp;TO
#### 22 : MyBatis_映射文件_参数处理_参数封装扩展思考, 见课件
#### 23 : MyBatis_源码分析_参数处理_参数封装map的过程, 见脑图
#### 24 : MyBatis_映射文件_参数处理_#与$取值区别
#### 25 : MyBatis_映射文件_参数处理_#取值时指定参数相关规则
#### 26 : MyBatis_映射文件_select_返回List
#### 27 : MyBatis_映射文件_select_记录封装map
#### 28 : MyBatis_映射文件_select_resultMap_自定义结果映射规则
#### 29 : MyBatis_映射文件_select_resultMap_关联查询_环境搭建
#### 30 : MyBatis_映射文件_select_resultMap_关联查询_级联属性封装结果
#### 31 : MyBatis_映射文件_select_resultMap_关联查询_association定义关联对象封装规则
#### 32 : MyBatis_映射文件_select_resultMap_关联查询_association分步查询
#### 33 : MyBatis_映射文件_select_resultMap_关联查询_分步查询&延迟加载
#### 34 : MyBatis_映射文件_select_resultMap_关联查询_collection定义关联集合封装规则
#### 35 : MyBatis_映射文件_select_resultMap_关联查询_collection分步查询&延迟加载
#### 36 : MyBatis_映射文件_select_resultMap_分步查询传递多列值&amp;amp;fetchType
#### 37 : MyBatis_映射文件_select_resultMap_discriminator鉴别器
### MyBatis_04_DynamicSQL
#### 38 : MyBatis_动态sql_简介&环境搭建, 见课件
#### 39 : MyBatis_动态sql_if_判断&amp;amp;OGNL
#### 40 : MyBatis_动态sql_where_查询条件
#### 41 : MyBatis_动态sql_trim_自定义字符串截取
#### 42 : MyBatis_动态sql_choose_分支选择
#### 43 : MyBatis_动态sql_set_与if结合的动态更新
#### 44 : MyBatis_动态sql_foreach_遍历集合
#### 45 : MyBatis_动态sql_foreach_mysql下foreach批量插入的两种方式
#### 46 : MyBatis_动态sql_foreach_oracle下批量插入的两种方式
#### 47 : MyBatis_动态sql_foreach_oracle下foreach批量保存两种方式
#### 48 : MyBatis_动态sql_内置参数_parameter&_databaseId
#### 49 : MyBatis_动态sql_bind_绑定
#### 50 : MyBatis_动态sql_sql_抽取可重用的sql片段
### MyBatis_05_cache
#### 51 : MyBatis_缓存_缓存介绍, 见课件
#### 52 : MyBatis_缓存_一级缓存体验
#### 53 : MyBatis_缓存_一级缓存失效的四种情况
#### 54 : MyBatis_缓存_二级缓存介绍, 见课件
#### 55 : MyBatis_缓存_二级缓存使用&细节
#### 56 : MyBatis_缓存_缓存有关的设置以及属性
#### 57 : MyBatis_缓存_缓存原理图示, 见课件
#### 58 : MyBatis_缓存_第三方缓存整合原理&ehcache适配包下载,见课件
#### 59 : MyBatis_缓存_MyBatis整合ehcache&总结
### MyBatis_06_ssm
#### 60 : MyBatis_整合Spring_整合适配包下载,见课件
#### 61 : MyBatis_整合Spring_所有需要的jar包导入,见课件
#### 62 : MyBatis_整合Spring_引入MyBatis之前的配置,见课件
#### 63 : MyBatis_整合Spring_SpringMVC配置文件编写,见课件
#### 64 : MyBatis_整合Spring_Spring配置文件编写,见课件
#### 65 : MyBatis_整合Spring_Spring整合MyBatis关键配置,见课件
#### 66 : MyBatis_整合Spring_整合测试,见课件
### MyBatis_07_mbg
#### 67 : MyBatis_逆向工程_mbg简介,见课件
#### 68 : MyBatis_逆向工程_mgb配置文件编写,见课件
#### 69 : MyBatis_逆向工程_使用mbg逆向生成所有代码及配置
#### 70 : MyBatis_逆向工程_测试简单查询&测试带条件复杂查询
### 脑图
#### 71 : MyBatis_运行原理_框架分层架构
#### 72 : MyBatis_运行原理_调试前注意
#### 73 : MyBatis_运行原理_SQLSessionFactory的初始化
#### 74 : MyBatis_运行原理_openSession获取SqlSession对象
#### 75 : MyBatis_运行原理_getMapper获取到接口的代理对象
#### 76 : MyBatis_运行原理_查询实现
#### 77 : MyBatis_运行原理_查询流程总结
#### 78 : MyBatis_运行原理_MyBatis原理总结
#### 79 : MyBatis_插件_插件原理
#### 80 : MyBatis_插件_插件编写&amp;amp;单个插件原理
#### 81 : MyBatis_插件_多个插件运行流程
#### 82 : MyBatis_插件_开发插件
### mybatis_11_other
#### 83 : MyBatis_扩展_分页_PageHelpler分页插件使用
#### 84 : MyBatis_扩展_批量_BatchExecutor&Spring中配置批量sqlSession, 见MyBatis_06_ssm
#### 85 : MyBatis_扩展_存储过程_oracle中创建一个带游标的存储过程, 见课件
#### 86 : MyBatis_扩展_存储过程_MyBatis调用存储过程
#### 87 : MyBatis_扩展_自定义类型处理器_MyBatis中枚举类型的默认处理
#### 88 : MyBatis_扩展_自定义类型处理器_使用自定义的类型处理器处理枚举类型
#### 89 : MyBatis_运行原理_查询实现