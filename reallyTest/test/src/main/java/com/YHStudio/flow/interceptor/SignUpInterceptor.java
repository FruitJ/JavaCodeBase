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
* @date 创建时间：2018年9月21日 下午4:27:46 
* @version 1.0 
* @ClassName: SignUpInterceptor
* 注册请求拦截器
*/
@Component(value="signUpInterceptor")
public class SignUpInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier(value="checkArgs")
	private CheckArgs<Object> checkArgs;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 设置字符集编码
		request.setCharacterEncoding("utf-8");
		// 获取参数(进行参数验证)
		String userName = request.getParameter("userName");
		Integer userAge = Integer.parseInt(request.getParameter("userAge"));
		String userSex = request.getParameter("userSex");
		String userPhone = request.getParameter("userPhone");
		Integer userLevel = Integer.parseInt(request.getParameter("userLevel"));
		String e_mail = request.getParameter("e_mail");
		String userPwd = request.getParameter("userPwd");
		// 指明验证规则
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CheckArgs.CN_NAME, userName);
		map.put(CheckArgs.DF_AGE, userAge);
		map.put(CheckArgs.DF_SEX, userSex);
		map.put(CheckArgs.CN_PHONE, userPhone);
		map.put(CheckArgs.CUS_LEVEL, userLevel);
		map.put(CheckArgs.CN_EMAIL, e_mail);
		map.put(CheckArgs.DF_PWD, userPwd);
		
		// 对参数进行安全验证
		Boolean bol = checkArgs.check(map);
		if(bol) {
			return true;
		}else {
			// 此处重定向要特别注意
			response.sendRedirect("toSignUp");
			return false;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
