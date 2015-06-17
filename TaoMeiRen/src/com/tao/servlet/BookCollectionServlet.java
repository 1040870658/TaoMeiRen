package com.tao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.service.CollectionService;
import com.tao.service.CollectionTradeService;
import com.tao.service.CommodityService;
import com.tao.service.TradeService;
import com.tao.utils.StaticUtil;

public class BookCollectionServlet extends HttpServlet {
	private User user;
	private int num;
	private Commodity commodity;
	private CollectionService commodityService;
	private CollectionTradeService tradeService;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		user = (User) request.getSession().getAttribute("sessionUser");
		if (null == user || request.getSession() == null) {
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			return;
		}
		tradeService = new CollectionTradeService();
		commodityService = new CollectionService();
		num = Integer
				.parseInt((String) request.getParameter("item_number"));
		commodity = (Commodity) request.getSession().getAttribute(
				"commodityOfOrder");
		boolean success;
		Collection collection = commodityService.queryCollection(commodity);
		if (collection.getCurrentNum() < collection.getActiveNum()) {
			success = tradeService.book(commodity, num, user);
			commodityService.updateOrderNum(collection, num);
			returnResult(success, request, response,"预定成功");
		}
		else{
			success = tradeService.trade(commodity, num, user);
			returnResult(success, request, response,"团购成功");
		}
		request.setAttribute("detail_collection", collection);
		request.setAttribute("detail_commodity", commodity);
		request.getRequestDispatcher("/shop/collection_detail.jsp").forward(request,
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
			commodityService.removeCommodity(commodity, num);
			request.setAttribute("tips", successTips);
			return;
		} else {
			if (num == 0)
				request.setAttribute("tips", "额,0个怎么买...");
			else
				request.setAttribute("tips", "soooorry,你不够钱!!!");
			return;
		}
	}
}
