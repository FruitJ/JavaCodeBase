package com.YHStudio.CDYY.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * @author 刘杰
 * @version 1.0
 * 2018年7月10日
 * 类的作用:存储用户的注册信息
 */
public class EnrollService {

	private String phone;
	private String name;
	private String pwd;
	// 创建无参构造器
	public EnrollService() {
		super();
	}
	public EnrollService(String name, String phone, String pwd) {
		this.phone = phone;
		this.name = name;
		this.pwd = pwd;
	}
	/**
	 * 保存用户的注册信息
	 * @return
	 */
	@SuppressWarnings("static-access")
	public boolean saveInfo() {
		String driver = "com.mysql.jdbc.Driver";// 驱动信息
		String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=UTF-8";// 连接信息
		String userName = "root";// 用户名
		String userPwd = "lj5211314";// 密码
		int num = 0;
		try {
			getClass().forName(driver);
			Connection conn = DriverManager.getConnection(url, userName, userPwd);// 打开连接数据库的通道
			Statement statement = conn.createStatement(); 
			String sql = "insert into person(name, phone, password) value('"+ name +"','"+ phone +"','"+ pwd +"')";
			num = statement.executeUpdate(sql);//  执行sql语句
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(num > 0) {
			return true;
		}else {
			return false;
		}
	}
}
