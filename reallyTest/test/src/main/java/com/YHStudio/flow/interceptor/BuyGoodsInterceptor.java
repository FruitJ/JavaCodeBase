package com.YHStudio.flow.interceptor;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
 * @date 创建时间：2018年9月23日 上午9:51:56
 * @version 1.0
 * @ClassName: BuyGoodsInterceptor
 * 购物请求拦截器
 */
@Component(value = "buyGoodsInterceptor")
public class BuyGoodsInterceptor implements HandlerInterceptor {

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

		// 获取cookie集合
		Cookie[] cookies = request.getCookies();
		// 将 cookies 集合封装进map集合中
		Map<String, String> map = cookiesUtil.getCookies(cookies);
		if (map != null && !map.isEmpty()) {// 客户端 cookie 可以信任
			if(map.containsKey("YHStudio")) {// 在 map 容器中查找我们颁发过的 cookie 值

				String cookieStr = map.get("YHStudio");
				// 分割 cookie 信息
				String[] split = cookieStr.split("&");
				// 反序列化 cookie 信息
				String userPhone = new String(decoder.decode(split[0]), "UTF-8");
				String userName = new String(decoder.decode(split[1]), "UTF-8");
				// 根据 cookie 中的数据查询数据库
				List<CustomEntity> data = signService.queryCookieOfInfo(userPhone, userName);
				if (data != null && !data.isEmpty()) {// 用户处于登录状态

					// 定义表名
					String tabName = "s" + userPhone;
					// 获取用户商品表的状态值
					Boolean bol = signService.queryTableIsExists("flowDB", tabName);
					if (!bol) {// 不存在此表
						// 创建这张表
						try {// 表创建成功
							signService.createCusGoodsTab(tabName);
						} catch (Exception e) {// 说明表没有创建成功
							// 重定向到登录页
							response.sendRedirect("toSignIn");
							return false;
						}
					}
					// 共享表名
					HttpSession session = request.getSession();
					session.setAttribute("tabName", tabName);
					return true;
				}
			}
			


		}
		// 用户处于非登录状态
		// 为空返回登录
		response.sendRedirect("toSignIn");
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
