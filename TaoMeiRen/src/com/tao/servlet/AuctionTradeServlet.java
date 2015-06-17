package com.tao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Auction;
import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.service.AuctionService;
import com.tao.service.AuctionTradeService;
import com.tao.service.CollectionService;
import com.tao.service.CollectionTradeService;
import com.tao.utils.StaticUtil;

public class AuctionTradeServlet extends HttpServlet{
	private User user;
	private double price;
	private Commodity commodity;
	private AuctionService commodityService;
	private AuctionTradeService tradeService;
	private Auction auction;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		user = (User) request.getSession().getAttribute("sessionUser");
		if (null == user || request.getSession() == null) {
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			return;
		}
		tradeService = new AuctionTradeService();
		commodityService = new AuctionService();
		price = Double
				.parseDouble((String) request.getParameter("ask_price"));
		commodity = (Commodity) request.getSession().getAttribute(
				"commodityOfOrder");
		boolean success;
		auction = commodityService.queryAuction(commodity);
		if (price >= commodity.getPrice()) {
			success = tradeService.askPrice(commodity, auction, user, price);
			success = tradeService.trade(commodity, 1, user);
			returnResult(success, request, response,"买断成功");
			commodityService.removeCommodity(commodity, 1);
		}
		else{
			success = tradeService.askPrice(commodity,auction, user, price);
			returnResult(success, request, response,"要价成功");
		}
		request.setAttribute("detail_auction", auction);
		request.setAttribute("detail_commodity", commodity);
		request.getRequestDispatcher("/shop/auction_detail.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	private void returnResult(boolean success,HttpServletRequest request,HttpServletResponse response,String successTips)throws ServletException, IOException{
		if (success) {
			request.getSession().setAttribute("sessionUser", user);
			StaticUtil.updateContext(request, response, commodity.getType());
			request.setAttribute("tips", successTips);
			return;
		} else {
			if(price <= auction.getCurrentPrice())
				request.setAttribute("tips", "出价太低了");
			else
				request.setAttribute("tips", "soooorry,你不够钱!!!");
			return;
		}
	}
}
