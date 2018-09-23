package com.YHStudio.flow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.YHStudio.flow.dao.Mapper;
import com.YHStudio.flow.entities.CustomEntity;
import com.YHStudio.flow.utils.TestCodeGenerator;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月21日 下午5:37:25 
* @version 1.0 
* @ClassName: SignService
*/
@Service(value="signService")
public class SignService {
	
	@Autowired
	@Qualifier(value="codeGenerator")
	private TestCodeGenerator codeGenerator;
	
	@Autowired
	@Qualifier(value="achieve")
	private Mapper achieve;
	
	// 添加用户信息
	@Transactional
	public Integer addCustomInfo(CustomEntity customEnt) {
		return achieve.addCustomInfo(customEnt);
	}
	
	// 查询用户
	@Transactional
	public Boolean queryCustomIsState(String userPhone) {
		List<CustomEntity> map = achieve.queryCustomIsState(userPhone);
		if(map != null && !map.isEmpty()) return true;
		return false;
	}
	
	// 获取验证码
	public String getTestCode(Integer digit) {
		
		return codeGenerator.getTestCode(digit);
	}

	// 查询用户
	@Transactional
	public List<CustomEntity> queryCustomInfo(String userPhone, String userPwd) {
		
		return achieve.queryCustomInfo(userPhone, userPwd);
	}
	
	// 通过 cookie 中的信息查询用户
	@Transactional
	public List<CustomEntity> queryCookieOfInfo(String userPhone, String userName) {
		return achieve.queryCookieOfInfo(userPhone, userName);
	}
	
	// 查询用户商品表的状态
	@Transactional
	public Boolean queryTableIsExists(String dbName, String tab) {
		String isExists = achieve.queryTableIsExists(dbName,tab);
		if(isExists != null) return true;
		return false;
	}
	
	// 创建用户商品表
	@Transactional
	public void createCusGoodsTab(String tabName) {
		achieve.createCusGoodsTab(tabName);
	}
	
	// 添加购买商品信息
	@Transactional
	public Boolean addShopInfo(List<Object> list) {
		int num = achieve.addShopInfo(list);
		if(num > 0) return true;
		return false;
	}
}
