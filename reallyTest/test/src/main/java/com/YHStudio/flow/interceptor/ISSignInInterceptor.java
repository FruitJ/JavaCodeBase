package com.YHStudio.flow.interceptor;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.YHStudio.flow.entities.CustomEntity;
import com.YHStudio.flow.service.SignService;
import com.YHStudio.flow.utils.CookiesUtil;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月23日 下午1:47:40 
* @version 1.0 
* @ClassName: ISSignInInterceptor
*/
@Component(value="iSSignInInterceptor")
public class ISSignInInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier(value = "signService")
	private SignService signService;
	
	@Autowired
	@Qualifier(value="cookiesUtil")
	private CookiesUtil cookiesUtil;

	private final Base64.Decoder decoder = Base64.getDecoder();
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取cookie值
		Cookie[] cookies = request.getCookies();
		if(cookies != null && !Arrays.asList(cookies).isEmpty()) {
			// 将 cookies 放进map集合中
			Map<String, String> map = cookiesUtil.getCookies(cookies);
			// 取出对应的 cookie 信息
			String cookieStr = map.get("YHStudio");
			if(map.containsKey("YHStudio")) {
				// 分割 cookie 信息
				String[] split = cookieStr.split("&");
				// 根据 cookie 信息查询用户
				List<CustomEntity> data = signService.queryCookieOfInfo(new String(decoder.decode(split[0]), "UTF-8"), new String(decoder.decode(split[1])));
				if(data != null && !data.isEmpty()) {// 登录状态
					// 共享权限值 -> 
					request.getSession().setAttribute("cus_level", data.get(0).getUserLevel());
					// 获取权限值 -> 该用户是否拥有权限
					if(data.get(0).getUserLevel() > 1) {// 具有该项权限 -> 放行 
						response.getWriter().print("{\"state\":\"has level\"}");
					}else {// 不具有该项权限 -> 提示
						response.getWriter().print("{\"state\":\"no level\"}");
					}
				}else {// 非登录状态
					response.getWriter().print("{\"state\":\"no signIn\"}");
				}
			}else {
				response.getWriter().print("{\"state\":\"no signIn\"}");
			}
			
		}else {
			response.getWriter().print("{\"state\":\"no signIn\"}");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
