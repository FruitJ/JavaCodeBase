package com.YHStudio.flow.interceptor;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.YHStudio.flow.service.SignService;
import com.YHStudio.flow.utils.CookiesUtil;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月23日 上午7:54:57 
* @version 1.0 
* @ClassName: CookiesInterceptor
*/
@Component(value="cookiesInterceptor")
public class CookiesInterceptor implements HandlerInterceptor {

	
	@Autowired
	@Qualifier(value = "signService")
	private SignService signService;
	
	@Autowired
	@Qualifier(value="cookiesUtil")
	private CookiesUtil cookiesUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 获取所有的 cookie 集合
		Cookie[] cookies = request.getCookies();
		// 判空
		if(cookies != null && !Arrays.asList(cookies).isEmpty()) {
			// 将所有的 cookie 信息封进map集合中
			Map<String, String> map = cookiesUtil.getCookies(cookies);
			
			// 判断map集合中的 keys 是否含有本网站颁发过的cookie名称
			if(map.containsKey("YHStudio")) {
				return true;
			}
		}
		// 如没有 -> 返回视图并设置数据
		request.setAttribute("userName", "go");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
