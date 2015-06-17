package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.service.CommodityService;
import com.tao.service.TradeService;
import com.tao.utils.StaticUtil;

public class TradeServlet extends HttpServlet{

	private User user;
	private CommodityService commodityService;
	private TradeService tradeService;
	@Override
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		user = (User)request.getSession().getAttribute("sessionUser");
		if(null == user || request.getSession() == null){
			response.sendRedirect(request.getContextPath()+"/user/login.jsp");
			return;
		}
		tradeService = new TradeService();
		commodityService = new CommodityService();
		int num = Integer.parseInt((String)request.getParameter("item_number"));
		Commodity commodity = (Commodity)request.getSession().getAttribute("commodityOfOrder");
		boolean success = tradeService.trade(commodity, num, user);
		if(success){
			request.setAttribute("tips", "购买成功");
			request.getSession().setAttribute("sessionUser", user);
			commodityService.removeCommodity(commodity,num);
			StaticUtil.updateContext(request, response, commodity.getType());
			request.setAttribute("detail_commodity", commodity);
		}
		else{
			if(num == 0){
				System.out.println("fail,额,0个怎么买...");
				request.setAttribute("tips", "额,0个怎么买...");
			}
			else{
				request.setAttribute("tips", "soooorry,你不够钱!!!");
			}
			request.setAttribute("detail_commodity", commodity);
		}
		request.getRequestDispatcher("/shop/detail.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
