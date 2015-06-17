package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.Init;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.model.User;
import com.tao.service.CommodityService;
import com.tao.service.TradeService;
import com.tao.service.UserService;
import com.tao.utils.CommodityServiceFactory;
import com.tao.utils.StaticUtil;

/**
 * UserServlet层
 * 
 * @author cxf
 * 
 */
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 请求编码(POST)
		response.setContentType("text/html;charset=utf-8");// 响应编码

		UserService userService = new UserService();
		int type = Integer.parseInt(request.getParameter("log_type"));
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		if (type == 1) {
			if (form.getEmail().equals("Yechen")) {
				if (form.getPassword().equals("888888")) {				
					TradeService tradeService = new TradeService();
					ArrayList<Order> orders = tradeService.queryAllOrders();
					request.setAttribute("orders", orders);
					request.getRequestDispatcher(
						  "/user/SuperUser.jsp")
							.forward(request, response);
					return;
				}
			}
		}
		initContext(request, response);
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("validate", "");
			request.getSession().setAttribute("sessionUser", user);
			request.getRequestDispatcher("/user/welcome.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getSession().setAttribute("validate", "密码验证失败");
			// request.getRequestDispatcher("/user/login.jsp").forward(request,
			// response);
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	private void initContext(HttpServletRequest request,
			HttpServletResponse response) {
		StaticUtil.updateContext(request, response, Commodity.FIX);
	}
}
