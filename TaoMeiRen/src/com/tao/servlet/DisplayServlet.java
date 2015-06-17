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
import com.tao.utils.CommodityServiceFactory;
import com.tao.utils.StaticUtil;

public class DisplayServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int type = Integer.parseInt( request.getParameter("display_type") );
		CommodityService commodityService= new CommodityServiceFactory().createService(type);
		ArrayList<Commodity> all_commodities = commodityService.queryAllCommodity();
		int size = all_commodities.size();
		int index = 0;
		String indexString = request.getParameter("page");
		if(indexString != null){
			index = 16*(Integer.parseInt(indexString)-1);
		}
		all_commodities = commodityService.seperateConceret(index, 16, all_commodities);
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
