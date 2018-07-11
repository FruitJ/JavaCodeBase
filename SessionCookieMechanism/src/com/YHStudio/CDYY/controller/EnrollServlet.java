package com.YHStudio.CDYY.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YHStudio.CDYY.service.EnrollService;

/**
 * Servlet implementation class EnrollServlet
 */
@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符编码集
		request.setCharacterEncoding("utf-8");
		// 获取前台参数
		String phone = request.getParameter("phone");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		// 获取EnrollService类的实例
		EnrollService enrollService = new EnrollService(userName, phone, userPwd);
		// 调用Service层的Xxx方法进行数据插入并获取返回值
		boolean bol = enrollService.saveInfo();
		// 请求转发
		if(bol) {
			request.getRequestDispatcher("EnrollSuccess.jsp").forward(request, response);
		}
	}

}
