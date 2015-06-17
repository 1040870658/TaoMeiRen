package com.tao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.tao.model.Auction;
import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.service.CommodityService;
import com.tao.utils.CommodityServiceFactory;

public class RegisterCommodityServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(null == user){
//			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
			response.sendRedirect("user/login.jsp");
			return;
		}
		Commodity commodity = CommonUtils.toBean(request.getParameterMap(), Commodity.class);
		String deadlineString = request.getParameter("deadline");
		java.sql.Timestamp sqlDate;
		sqlDate = getDate(deadlineString);
		String activeNumString = request.getParameter("active_num");
		String activePriceString = request.getParameter("active_price");
		int activeNum = 0;
		int activePrice = 0;
		
		if(activeNumString != null && !activeNumString.equals(""))
			activeNum = Integer.parseInt(activeNumString);
		if(activePriceString != null && !activePriceString.equals(""))
			activePrice = Integer.parseInt(activePriceString);
		Collection collection = new Collection(0, commodity, sqlDate, activeNum, 0, commodity.getPrice(), new ArrayList<String>());
		Auction auction = new Auction(commodity, sqlDate, activePrice, 0, null, new ArrayList<String>(), 0);
		CommodityServiceFactory  commodityServiceFactory = new CommodityServiceFactory();
		String dealtype = (String) request.getParameter("dealtype");
		commodity.setType(Commodity.TYPE.get(dealtype));
		ArrayList<String>imageUrl = new ArrayList<String>();
		String imageString = request.getParameter("image_src");
		imageUrl.add(imageString);
		commodity.setImageUrl(imageUrl);
		
		CommodityService commodityService = commodityServiceFactory.createService(Commodity.TYPE.get(dealtype));
		if(commodityService.registerCommodity(user, commodity,collection,auction)){
			request.setAttribute("msg", "success");
		}
		else{
			request.setAttribute("msg", "failed");
		}
		request.getRequestDispatcher("/SellerServlet").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	private java.sql.Timestamp getDate(String deadlineString){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Timestamp sqlDate;
		Date deadlineDate;
		if(deadlineString == null || deadlineString.equals("") )
			return new java.sql.Timestamp(0);
		try {
			deadlineDate = sdf.parse(deadlineString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			deadlineDate = new Date();
			e.printStackTrace();
		}
		sqlDate = new java.sql.Timestamp(deadlineDate.getTime());
		return sqlDate;
	}
}
