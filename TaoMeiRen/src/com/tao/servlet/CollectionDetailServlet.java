package com.tao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.service.CollectionService;
import com.tao.service.CommodityService;

public class CollectionDetailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id;
		CollectionService commodityService = new CollectionService();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		id = Integer.parseInt(request.getParameter("order_detail_commodityID"));
		Commodity commodity = commodityService.queryCommodity(id);
		Collection collection = commodityService.queryCollection(commodity);
		request.setAttribute("detail_commodity", commodity);
		request.setAttribute("detail_collection", collection);
		request.getRequestDispatcher("user/collection_detail.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}