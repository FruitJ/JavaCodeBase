package com.YHStudio.flow.entities;

import org.springframework.stereotype.Component;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月21日 下午3:30:44 
* @version 1.0 
* @ClassName: CustomEntity
* custom 实体类
*/

@Component(value="customEnt")
public class CustomEntity {

	private int id;
	private String userName;
	private Integer userAge;
	private char userSex;
	private String userPhone;
	private int userLevel;
	private String e_mail;
	private String userPwd;

	// 无参构造器
	public CustomEntity() {
		super();
	}
	
	// 有参构造器
	public CustomEntity(int id, String userName, Integer userAge, char userSex, String userPhone, int userLevel, String e_mail, String userPwd) {
		super();
		this.id = id;
		this.userName = userName;
		this.userAge = userAge;
		this.userSex = userSex;
		this.userPhone = userPhone;
		this.userLevel = userLevel;
		this.e_mail = e_mail;
		this.userPwd = userPwd;
	}
	
	// 有参构造器
	public CustomEntity(String userName, Integer userAge, char userSex, String userPhone, int userLevel, String e_mail, String userPwd) {
		super();
		this.userName = userName;
		this.userAge = userAge;
		this.userSex = userSex;
		this.userPhone = userPhone;
		this.userLevel = userLevel;
		this.e_mail = e_mail;
		this.userPwd = userPwd;
	}

	// 对应的 setter 与 getter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public char getUserSex() {
		return userSex;
	}

	public void setUserSex(char userSex) {
		this.userSex = userSex;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "CustomEntity [id=" + id + ", userName=" + userName + ", userAge=" + userAge + ", userSex=" + userSex
				+ ", userPhone=" + userPhone + ", userLevel=" + userLevel + ", e_mail=" + e_mail + ", userPwd="
				+ userPwd + "]";
	}
}
