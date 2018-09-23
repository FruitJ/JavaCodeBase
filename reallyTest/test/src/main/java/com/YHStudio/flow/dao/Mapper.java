package com.YHStudio.flow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.YHStudio.flow.entities.CustomEntity;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月21日 下午5:50:11 
* @version 1.0 
* @ClassName: Mapper
*/
public interface Mapper {

	// 注册信息
	public abstract int addCustomInfo(CustomEntity custom);
	// 注册/登录信息的用户是否存在的验证
	public abstract List<CustomEntity> queryCustomIsState(String userPhone);
	// 登录信息
	public abstract List<CustomEntity> queryCustomInfo(@Param("userPhone") String userPhone, @Param("userPwd") String userPwd);
	// 通过cookie信息查询数据库中的数据是否匹配
	public abstract List<CustomEntity> queryCookieOfInfo(@Param("userPhone") String userPhone, @Param("userName") String userName);
	// 判断数据库中是否存在对应用户的商品表
	public abstract String queryTableIsExists(@Param("databaseName") String dbName, @Param("tabName")String tab);
	// 创建表
	public abstract boolean createCusGoodsTab(@Param("tabName")String tabName);
	// 添加商品	
	public abstract int addShopInfo(List<Object> list);
}
