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

public class SellerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommodityService commodityService = new CommodityService();
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(null == user){
			response.sendRedirect("user/login.jsp");
			return;
		}
		ArrayList<Commodity> commodities  = commodityService.queryCommodity(user);
		ArrayList<Commodity> fix = new ArrayList<Commodity>();
		ArrayList<Commodity> collection = new ArrayList<Commodity>();
		ArrayList<Commodity> auction = new ArrayList<Commodity>();
		commodityService.seperateCommodity(commodities, fix, collection, auction);
		for(Commodity e:fix)
			System.out.println(e.getName());
		for(Commodity e:collection)
			System.out.println(e.getName());
		for(Commodity e:auction)
			System.out.println(e.getName());
		request.setAttribute("fixes", fix);
		request.setAttribute("collections", collection);
		request.setAttribute("auctions", auction);
		request.getServletContext().getRequestDispatcher("/user/seller.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
