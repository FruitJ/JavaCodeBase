package com.YHStudio.flow.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Component;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月23日 上午7:44:25 
* @version 1.0 
* @ClassName: CookiesIsReady
*/
@Component(value="cookiesUtil")
public class CookiesUtil {

	public Map<String, String> getCookies(Cookie[] cookies) {
		Map<String, String> map = new HashMap<String, String>();
		for (Cookie item : cookies) {
			map.put(item.getName(), item.getValue());
		}
		return map;
	}
}
