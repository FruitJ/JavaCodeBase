package com.YHStudio.CDYY.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * @author 刘杰
 * @version 1.0
 * 2018年7月10日
 * 类的作用:查询数据库中有无用户信息
 */
public class LoginService {

	private String phone;
	private String pwd;
	
	// 创建无参构造器
	public LoginService() {
		super();
	}
	// 创建有参构造器
	public LoginService(String phone, String pwd) {
		this.phone = phone;
		this.pwd = pwd;
	}
	@SuppressWarnings("static-access")
	public boolean queryInfo() throws Exception {
		
		// 初始化链接数据库的相关信息
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=UTF-8";
		String userName = "root";
		String userPwd = "lj5211314";
		ResultSet resultSet = null;
		try {
			// 创建数据库驱动
			getClass().forName(driver);
			Connection conn = DriverManager.getConnection(url, userName, userPwd);
			// 链接数据库
			Statement statement = conn.createStatement();
			String sql = "select * from person where phone = '"+ phone +"' and password = '"+ pwd +"'";
			// 执行sql语句
			resultSet = statement.executeQuery(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
	}
}
