package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.model.User;
import com.tao.service.AuctionService;
import com.tao.service.AuctionTradeService;
import com.tao.service.CommodityService;
import com.tao.service.TradeService;
import com.tao.service.UserService;

public class ConfirmServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		TradeService tradeService = new TradeService();
		CommodityService commodityService = new CommodityService();
		UserService userService = new UserService();
		
		Order order = tradeService.queryOrder(Integer.parseInt(request.getParameter("orderID")));
		User seller = userService.queryUser(order.getMailOfseller());
		userService.sell(order, seller);
		tradeService.removeOrder(order);
		Commodity commodity = commodityService.queryCommodity(order.getCommodityID());
		commodityService.deleteCommodity(commodity);
		request.getRequestDispatcher("/BuyerServlet").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
