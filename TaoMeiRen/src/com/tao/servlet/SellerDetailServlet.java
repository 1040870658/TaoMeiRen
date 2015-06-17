package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Commodity;

public class SellerDetailServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int pos = Integer.parseInt(request.getParameter("position"));
		ArrayList<Commodity>commodities = (ArrayList<Commodity>) request.getSession().getAttribute("commodities");
		Commodity commodity = commodities.get(pos);
		request.setAttribute("detail_commodity", commodity);
		request.getRequestDispatcher("user/commodity_detail.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
