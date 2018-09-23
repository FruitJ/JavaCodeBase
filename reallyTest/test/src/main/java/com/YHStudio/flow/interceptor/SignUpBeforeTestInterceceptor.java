package com.YHStudio.flow.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.YHStudio.flow.utils.CheckArgs;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月22日 上午8:39:50 
* @version 1.0 
* @ClassName: SignUpBeforeTestInterceceptor
* 注册之前的请求拦截器
*/
@Component(value="signUpBeforeTestInterceceptor")
public class SignUpBeforeTestInterceceptor implements HandlerInterceptor{
	
	@Autowired
	@Qualifier(value="checkArgs")
	private CheckArgs<Object> checkArgs;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 设置字符编码集
		request.setCharacterEncoding("utf-8");
		// 创建验证参数的容器
		Map<String, Object> map = new HashMap<String, Object>();
		// 存储待验证的参数 -> 指明验证的规则
		map.put(CheckArgs.CN_PHONE, request.getParameter("userPhone"));
		// 对参数进行安全验证
		Boolean bol = checkArgs.check(map);
		if(bol) {
			return true;
		}else {
			response.getWriter().print("{\"state\":\"phone格式或长度有误\"}");
			return false;
		}
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
