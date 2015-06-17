package com.tao.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.tao.model.User;
import com.tao.service.UserService;

public class RegistServlet extends HttpServlet {
	private String verifyCode;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		Map<String,String> errors = new HashMap<String,String>();
		
		String username = form.getEmail();//获取表单的username
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length() < 3 || username.length() > 25) {
			errors.put("username", "用户名长度必须在3~15之间！");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if(password.length() < 3 || password.length() > 15) {
			errors.put("password", "密码长度必须在3~15之间！");
		}
		
		
		String sessionVerifyCode = (String) request.getSession().getAttribute("session_vcode");
		String verifyCode = request.getParameter("verifyCode");
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if(verifyCode.length() != 4) {
			errors.put("verifyCode", "验证码长度必须为4！");
		} else if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
			errors.put("verifyCode", "验证码错误！");
		}
		
		if(errors != null && errors.size() > 0) {
			request.getSession().setAttribute("msg", errors.get("verifyCode"));
			response.sendRedirect("user/regist.jsp");
			return;
		}
		
		try {
			request.getSession().setAttribute("msg",null );
			userService.regist(form);
			response.sendRedirect("user/success.jsp");
		} catch (Exception e) {
			request.getSession().setAttribute("msg", e.getMessage());
			//request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			response.sendRedirect("user/regist.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
}
