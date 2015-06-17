package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.dao.CommodityDao;
import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.service.CommodityService;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;

public class BuyerDetailServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		CommodityService commodityService = new CommodityService();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		id = Integer.parseInt(request.getParameter("order_detail_commodityID"));
		Commodity commodity = commodityService.queryCommodity(id);
		request.setAttribute("detail_commodity", commodity);
		request.getRequestDispatcher("user/buyer_detail.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
