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
* @date 创建时间：2018年9月22日 下午4:18:55 
* @version 1.0 
* @ClassName: SignInInterceptor
* 登录请求拦截器
*/
@Component(value="signInInterceptor")
public class SignInInterceptor implements HandlerInterceptor{

	@Autowired
	@Qualifier(value="checkArgs")
	private CheckArgs<Object> checkArgs;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String userPhone = request.getParameter("userPhone");
		String userPwd = request.getParameter("userPwd");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put(CheckArgs.CN_PHONE, userPhone);
		map.put(CheckArgs.DF_PWD, userPwd);
		// 对参数进行安全验证
		Boolean bol = checkArgs.check(map);
		if(bol) {
			return true;
		}else {
			response.sendRedirect("toSignIn");// 重定向到登录页面
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
