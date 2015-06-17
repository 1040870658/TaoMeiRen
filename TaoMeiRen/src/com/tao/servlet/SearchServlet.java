package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Commodity;
import com.tao.service.CommodityService;
import com.tao.utils.CommodityServiceFactory;

public class SearchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String searchName = (String)request.getParameter("search_name");
		int type = Integer.parseInt( request.getParameter("display_type") );
		CommodityService commodityService= new CommodityServiceFactory().createService(type);
		ArrayList<Commodity> all_commodities = commodityService.queryCommodity(searchName);
		int size = all_commodities.size();
		if(all_commodities.size() == 0)
			all_commodities = null;
		request.getServletContext().setAttribute("size", size);
		request.getServletContext().setAttribute("all_concrete_commodities", all_commodities);
		request.getRequestDispatcher("shop/shop.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
