package com.YHStudio.flow.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月21日 下午4:40:28 
* @version 1.0 
* @ClassName: CheckArgs
* 参数安全验证
*/
@Component(value="checkArgs")
public class CheckArgs<T> {

	public static final String CN_NAME = "CN_NAME";
	public static final String CN_PHONE = "CN_PHONE";
	public static final String CN_EMAIL = "CN_EMAIL";
	public static final String DF_AGE = "DF_AGE";
	public static final String DF_SEX = "DF_SEX";
	public static final String DF_PWD = "DF_PWD";
	public static final String CUS_LEVEL = "CUS_LEVEL";
	
	public Boolean check(Map<String, T> map) {
		if(map != null) {
			// 遍历map集合
			for(Entry<String, T> item : map.entrySet()) {
				String key = item.getKey().toString();
				String val = item.getValue().toString();
				switch(key) {// 定义规则
				case "CN_NAME":// 中文名称
					if(val.length() > 0 && val.length() <= 9) {
						if(!val.matches("[\u4e00-\u9fa5]{1,9}")) {
							return false;
						}
					}else {
						return false;
					}
					break;
				case "CN_PHONE":// 中国电话
					if(val.length() == 11) {
						if(!val.matches("^1[34578][0-9]\\d{8}")) {
							return false;
						}
					}else {
						return false;
					}
					break;
				case "CN_EMAIL":// 中国 E-mail
					if(!val.matches("^[0-9a-z]+@[0-9a-z]+\\.(?:com.cn|(?:com|cn))$")) {
						return false;
					}
					break;
				case "DF_AGE":	// 默认年龄
					Integer age_num = IntegerTypeChange(val);
					if(age_num == 0) {
						return false;
					}else {
						if(!(age_num > 0 && age_num < 120)) {
							return false;
						}
					}
					
					break;
				case "DF_SEX":	// 默认性别
					if(!("男".equals(val) || "女".equals(val))) {
						return false;
					}
					break;
				case "CUS_LEVEL": //默认级别 
					Integer level_num = IntegerTypeChange(val);
					if(level_num == 0) {
						return false;
					}else {
						if(!(level_num >=1 && level_num <= 3)) {
							return false;
						}
					}
					
					break;
				case "DF_PWD":// 默认密码
					if(!(val.length() == 6 && val.matches("^[a-zA-Z0-9]\\w{5}"))) {
						return false;
					}
					break;
				}
			}
		}
		
		
		return true;
	}
	
	private Integer IntegerTypeChange(String waitStr) {
		Integer it = 0;
		try {
			it = Integer.valueOf(waitStr);
		}catch(NumberFormatException e) {
			return 0;
		}
		return it;
	}
	
}
