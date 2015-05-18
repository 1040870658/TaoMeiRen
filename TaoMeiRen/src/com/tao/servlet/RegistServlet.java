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
		} else if(username.length() < 3 || username.length() > 15) {
			errors.put("username", "用户名长度必须在3~15之间！");
		}
		
		// 对密码进行校验
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if(password.length() < 3 || password.length() > 15) {
			errors.put("password", "密码长度必须在3~15之间！");
		}
		
		
		// 对验证码进行校验
		String sessionVerifyCode = (String) request.getSession().getAttribute("session_vcode");
		String verifyCode = request.getParameter("verifyCode");
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if(verifyCode.length() != 4) {
			errors.put("verifyCode", "验证码长度必须为4！");
		} else if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
			errors.put("verifyCode", "验证码错误！");
		}
		
		/*
		 * 判断map是否为空，不为空，说明存在错误
		 */
		if(errors != null && errors.size() > 0) {
			/*
			 * 1. 保存errors到request域
			 * 2. 保存form到request域，为了回显
			 * 3. 转发到regist.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		
		
		
		/*
		 * 2. 调用userService的regist()方法，传递form过去
		 * 3. 得到异常：获取异常信息，保存到request域，转发到regist.jsp中显示
		 * 4. 没有异常：输出注册成功！
		 */
		try {
			userService.regist(form);
			response.getWriter().print("<h1>注册成功！</h1><a href='" + 
					request.getContextPath() + 
					"/user/login.jsp" + "'>点击这里去登录</a>");
		} catch (Exception e) {
			// 获取异常信息，保存到request域
			request.setAttribute("msg", e.getMessage());
			// 还要保存表单数据，到request域
			request.setAttribute("user", form);//用来在表单中回显！
			// 转发到regist.jsp
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
}
