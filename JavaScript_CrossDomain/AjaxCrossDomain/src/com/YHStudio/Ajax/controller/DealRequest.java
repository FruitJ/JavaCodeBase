package com.YHStudio.Ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DealRequest
 */
@WebServlet("/dealRequest")
public class DealRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集编码
		request.setCharacterEncoding("utf-8");
		// 接收前台请求
		String name = request.getParameter("name");
		System.out.println("name:" + name);
		// 设置响应头
		// 控制可以接近的源
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
		// 控制可以接近的请求方式
		response.setHeader("Access-Control-Allow-Methods", "POST");
		// 控制可以接近的请求头
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		
		
		// 返回响应
		response.getWriter().print("132s");
	}

}
