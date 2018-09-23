package com.YHStudio.flow.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.YHStudio.flow.entities.CustomEntity;
import com.YHStudio.flow.service.SignService;
import com.YHStudio.flow.utils.CookiesUtil;

/**
 * @author : 刘杰
 * @date 创建时间：2018年9月21日 下午2:59:19
 * @version 1.0
 * @ClassName: DealAccessReqController
 * 控制器
 */
@RequestMapping(value = "dealAccessReq")
@Controller(value = "dealAccessReq")
public class DealAccessReqController {

	// 定义返回的视图
	private static final String SUCCESS = "success";
	
	@Autowired
	@Qualifier(value = "signService")
	private SignService signService;// 从IOC容器中拿到 Service 层中的实例

	@Autowired
	@Qualifier(value="cookiesUtil")
	private CookiesUtil cookiesUtil;
	
	
	// 借助控制器方法来访问 WEB-INF 下面的视图(SignUp.jsp)
	@RequestMapping(value = "toSignUp")
	public String toSignUp() {
		return "SignUp";
	}

	// 注册模块
	@RequestMapping(value = "dealSignUp")
	public ModelAndView dealSignUp(CustomEntity customEnt) {
		// 用户注册
		Integer num = signService.addCustomInfo(customEnt);
		// 获取 ModelAndView 实例并设置视图
		ModelAndView mv = new ModelAndView("SignIn");
		if (num > 0) {// 如果用户注册成功
			// 将数据放进视图模型中
			mv.addObject("member", customEnt.getId());
			mv.addObject("userName", customEnt.getUserName());
			mv.addObject("userSex", customEnt.getUserSex());
			// 返回该实例
			return mv;
		}
		// 如果用户注册失败
		mv.addObject("member", 0);// 用来给用户从两种不同途径访问的时候看到的效果
		return mv;
	}

	// 注册时判断该用户是否存在(通过手机号来判断)
	@RequestMapping(value = "isExists")
	@ResponseBody
	public Map<String, Object> isExists(@RequestParam(value = "userPhone") String userPhone) {
		// 查询用户
		Boolean bol = signService.queryCustomIsState(userPhone);
		// 存放 Json 数据的容器
		Map<String, Object> map = new HashMap<String, Object>();
		// 响应状态
		if (bol) {
			map.put("state", "yes");
		} else {
			map.put("state", "no");
		}
		return map;
	}

	// 请求 JSON 格式的随机验证码
	@RequestMapping(value = "reqRandomTestCode")
	@ResponseBody
	public void reqRandomTestCode(@RequestParam(value = "digit") String digit, HttpServletResponse response) {
		// 返回随机的 json 格式的验证码
		Integer bit = 0;
		try {
			bit = Integer.parseInt(digit);
		} catch (Exception e) {// 默认值
			bit = 4;
		}
		// 获取指定位数的随机验证码
		String testCode = signService.getTestCode(bit);
		try {
			// 返回验证码
			response.getWriter().print("{\"testCode\":\"" + testCode + "\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 借助控制器方法来访问 WEB-INF 下面的视图(SignIn.jsp)
	@RequestMapping(value = "toSignIn")
	public String toSignIn() {

		return "SignIn";
	}

	final Base64.Encoder encoder = Base64.getEncoder();
	final Base64.Decoder decoder = Base64.getDecoder();

	// 登录模块
	@RequestMapping(value = "dealSignIn")
	public ModelAndView dealSignIn(@RequestParam(value = "userPhone") String userPhone,
			@RequestParam(value = "userPwd") String userPwd, HttpServletRequest request, HttpServletResponse response) {
		// 查询用户
		List<CustomEntity> data = signService.queryCustomInfo(userPhone, userPwd);
		// 获取 ModelAndView 实例
		ModelAndView mv = new ModelAndView();
		// 用户存在
		if (data != null && !data.isEmpty()) {
			// 获取 session 实例
			HttpSession session = request.getSession();

			String userNameBase64 = null;
			String userPhoneBase64 = null;
			try {
				// 对用户名与手机号进行 base64 编码
				userNameBase64 = encoder.encodeToString(data.get(0).getUserName().getBytes("UTF-8"));
				userPhoneBase64 = encoder.encodeToString(data.get(0).getUserPhone().getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 拼接 cookie 串
			String cookieStr = userPhoneBase64 + "&" + userNameBase64 + "&" + session.getId();
			// 获取 cookie 实例
			Cookie cookie = new Cookie("YHStudio", cookieStr);
			// 设置cookie的存活周期(设置cookie存活周期是一天)
			cookie.setMaxAge(60 * 60 * 24);
			// 共享 cookie
			session.setAttribute("cookie", cookie);
			CustomEntity custom = data.get(0);
			// 共享数据库查询出来的用户实例
			session.setAttribute("custom", custom);	
			// 向客户端发送cookie(手机号 + 用户名 + sessionID)
			response.addCookie(cookie);
			// 跳转到主页(借助奇技淫巧)
			mv.setViewName("../../fakeIndex");
			// 将用户姓名放进 request域 中
			mv.addObject("userName", custom.getUserName());
			return mv;
		} else {// 该用户不存在
			// 重定向到登录页
			mv.setViewName("redirect:" + "toSignIn");
			return mv;
		}
	}

	// go 主页
	@RequestMapping(value = "toIndex")
	public ModelAndView toIndex(HttpServletRequest request) {// 获取 cookie
		// 获取 ModelAndView 实例
		ModelAndView mv = new ModelAndView();
		// 获取存放所有cookie信息的 map 容器
		
		Map<String, String> cookies = cookiesUtil.getCookies(request.getCookies());
		// 获取本网站版发过的 cookie 值
		String cookieStr = cookies.get("YHStudio");
		// 分割 cookie
		String[] split = cookieStr.split("&");
		try {
			// 反序列化 cookie
			String userPhone = new String(decoder.decode(split[0]), "UTF-8");
			String userName = new String(decoder.decode(split[1]), "UTF-8");

			// 根据 cookie 查询用户
			List<CustomEntity> data = signService.queryCookieOfInfo(userPhone, userName);
			
			if (data != null && !data.isEmpty()) {// 用户登录过
				mv.setViewName("../../index");
				// 根据情况返回不同的视图模型
				mv.addObject("userName", data.get(0).getUserName());
				return mv;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mv.setViewName("../../index");
		mv.addObject("userName", "go");
		return mv;
	}

	// 创建查询用户登录状态的信息
	@RequestMapping(value="buyGoods")
	public String buyGoods(HttpServletRequest request, @RequestParam(value="goodsName") String goodsName, @RequestParam(value="price") String price) {

		// 从 session 实例中拿出指定的表名
		String tabName = (String) request.getSession().getAttribute("tabName");
		// 获取存放不同类型参数的 List集合
		List<Object> list = new ArrayList<Object>();
		list.add(tabName);// tab name
		list.add(tabName.replace("s", ""));// phone
		list.add(goodsName);// name
		list.add(new java.sql.Timestamp(System.currentTimeMillis()));// date
		list.add(Float.valueOf(price));// price
		// 获取存储商品的状态值
		Boolean bol = signService.addShopInfo(list);
		// 根据状态值跳转不同的视图
		if(bol) return SUCCESS;
		return "../../index";
	}
	
	// 判断用户是否登录
	@RequestMapping(value="isSignIn")
	@ResponseBody
	public void isSignIn(HttpServletRequest request) {
		System.out.println("验证登录 ...");
	}
	
	// 注销登录
	@RequestMapping(value="cancelSignIn")
	public String cancelSignIn(HttpServletRequest request, HttpServletResponse response) {
		// 获取 Session 
		HttpSession session = request.getSession();
		// 获取cookie
		Cookie cookie = (Cookie) session.getAttribute("cookie");
		if(cookie != null) {// 如果 cookie 对象存在
			cookie.setMaxAge(0);// 干掉 cookie
			response.addCookie(cookie);// 刷新浏览器端 cookie
		}
		return "../../fakeIndex";
	}
	
}
