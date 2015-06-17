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
import com.tao.service.CommodityService;
import com.tao.service.TradeService;

public class SellerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommodityService commodityService = new CommodityService();
		TradeService tradeService = new TradeService();
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(null == user){
			response.sendRedirect("user/login.jsp");
			return;
		}
		int commodity_type = Commodity.FIX;	
		String commodity_tmp = (String )request.getParameter("commodity_type");	
		if(commodity_tmp != null && !commodity_tmp.equals("")){
			commodity_type = Integer.parseInt(commodity_tmp);
		}		
		ArrayList<Commodity> commodities  = commodityService.queryCommodity(user);
		ArrayList<Commodity> fix = new ArrayList<Commodity>();
		ArrayList<Commodity> collection = new ArrayList<Commodity>();
		ArrayList<Commodity> auction = new ArrayList<Commodity>();
		commodityService.seperateCommodity(commodities, fix, collection, auction);	
		switch(commodity_type){
			case Commodity.FIX:request.getSession().setAttribute("commodities", fix);break;
			case Commodity.COLLECTIVE:request.getSession().setAttribute("commodities", collection);break;
			case Commodity.AUCTION:request.getSession().setAttribute("commodities", auction);break;
		};
		
		int order_type = Commodity.FIX;
		String order_tmp = (String)request.getParameter("order_type");
		if(order_tmp != null && !order_tmp.equals("")){
			order_type = Integer.parseInt(order_tmp);
		}
		ArrayList<Order> orders = tradeService.displayOrders(user, Order.SELLER);
		ArrayList<Order> fixOrders = new ArrayList<Order>();
		ArrayList<Order> collectionOrders = new ArrayList<Order>();
		ArrayList<Order> auctionOrders = new ArrayList<Order>();
		tradeService.seperateOrder(orders, fixOrders,collectionOrders,auctionOrders);
		
		switch(order_type){
			case Commodity.FIX:request.getSession().setAttribute("orders", fixOrders);break;
			case Commodity.COLLECTIVE:request.getSession().setAttribute("orders", collectionOrders);break;
			case Commodity.AUCTION:request.getSession().setAttribute("orders", auctionOrders);break;
		};
		request.getServletContext().getRequestDispatcher("/user/seller.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
