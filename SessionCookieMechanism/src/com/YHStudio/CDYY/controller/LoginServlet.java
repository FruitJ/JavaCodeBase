package com.YHStudio.CDYY.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.YHStudio.CDYY.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String userPwd = request.getParameter("userPwd");
		try {
			// 获取LoginService类的实例
			LoginService loginService = new LoginService(phone, userPwd);
			// 调用Service层的Xxx方法进行数据库查询  + 请求转发
			boolean bol = loginService.queryInfo();
			if(bol) {
				// 获取session实例
				HttpSession session = request.getSession();
				// 生成sessionId
				String sessionId = session.getId();
				// 获取Cookie实例
				Cookie cookie = new Cookie("CDYY", sessionId);
				// 向浏览器发送cookie
				response.addCookie(cookie);
				System.out.println("cookie发送成功 ...");
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}else {
				System.out.println("登录失败 ...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
