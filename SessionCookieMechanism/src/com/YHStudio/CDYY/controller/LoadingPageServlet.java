package com.YHStudio.CDYY.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadingPageServlet
 */
@WebServlet("/LoadingPageServlet")
public class LoadingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> data = new ArrayList<String>();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadingPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符编码集
		request.setCharacterEncoding("utf-8");
		// 接收前台的数据
		// 获取cookie
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			// 判断传递过来的cookie是否有名字带有CDYY的
			for (int i = 0; i < cookies.length; i++) {
				data.add(cookies[i].getName());
			}
			if(data.indexOf("CDYY") != -1) {// 判断获取到的Cookie中有没有名字叫做CDYY的
				// 如果有就进行请求转发(跳转到登录成功页面)
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}else {
				// 如果没有(跳转到登录页面)
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else {
			// 如果请求中没有传来Cookie(跳转到登录页面)
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
