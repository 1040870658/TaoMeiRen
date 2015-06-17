package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Order;
import com.tao.model.User;
import com.tao.service.TradeService;

public class BuyerServlet extends HttpServlet{

	private TradeService tradeService;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ArrayList<Order> orders = new ArrayList<Order>();
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(null == user){
			response.sendRedirect(request.getContextPath()+"/user/login.jsp");
			return;
		}
		tradeService = new TradeService();
		orders = tradeService.displayOrders(user,Order.BUYER);
		request.setAttribute("orders", orders);
		request.getServletContext().getRequestDispatcher("/user/buyer.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
