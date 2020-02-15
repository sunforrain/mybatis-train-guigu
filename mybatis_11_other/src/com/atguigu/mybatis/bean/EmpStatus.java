package com.atguigu.mybatis.bean;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * 视频87 typeHandler_mybatis中枚举类型的默认处理和更改
 * 视频88 使用自定义的类型处理器处理枚举类型
 * 希望数据库保存的是100,200这些状态码，而不是默认0,1或者枚举的名
 * @author lfy
 *
 */
public enum EmpStatus {
	LOGIN(100,"用户登录"),LOGOUT(200,"用户登出"),REMOVE(300,"用户不存在");
	
	// 定制的枚举,更常见的用法
	private Integer code;
	private String msg;
	private EmpStatus(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//按照状态码返回枚举对象
	public static EmpStatus getEmpStatusByCode(Integer code){
		switch (code) {
			case 100:
				return LOGIN;
			case 200:
				return LOGOUT;	
			case 300:
				return REMOVE;
			default:
				return LOGOUT;
		}
	}
	
	
}
