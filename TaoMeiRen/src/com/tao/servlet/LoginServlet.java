package com.tao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.tao.model.User;
import com.tao.service.UserService;

/**
 * UserServlet层
 * @author cxf
 *
 */
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		UserService userService = new UserService();
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("sessionUser", user);
			request.getRequestDispatcher("/user/welcome.jsp").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
