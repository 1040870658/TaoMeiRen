package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Commodity;
import com.tao.service.CommentService;
import com.tao.service.CommodityService;
import com.tao.utils.CommodityServiceFactory;

public class DropServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int pos = Integer.parseInt(request.getParameter("position"));
		ArrayList<Commodity>commodities = (ArrayList<Commodity>) request.getSession().getAttribute("commodities");
		Commodity commodity = commodities.get(pos);
		CommentService commentService = new CommentService();
		CommodityServiceFactory commodityServiceFactory = new CommodityServiceFactory();
		CommodityService commodityService = commodityServiceFactory.createService(commodity.getType());
		commentService.removePersonalComment(commodity);
		commodityService.deleteCommodity(commodity);
		request.getRequestDispatcher("SellerServlet").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
