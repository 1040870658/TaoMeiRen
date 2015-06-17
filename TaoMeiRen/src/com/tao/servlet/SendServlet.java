package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.model.User;
import com.tao.service.CommodityService;
import com.tao.service.MailService;
import com.tao.service.TradeService;

public class SendServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id;
		int pos;
		User user;
		ArrayList<Order> orders;
		MailService mailService = new MailService();
		CommodityService commodityService = new CommodityService();
		TradeService tradeService = new TradeService();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		orders = (ArrayList<Order>) request.getSession().getAttribute("orders");
		pos = Integer.parseInt( request.getParameter("position") );
		Order order = orders.get(pos);
		id = order.getCommodityID();
		Commodity commodity = commodityService.queryCommodity(id);
		try {
			String filename = getServletContext().getRealPath("/").replace("\\", "/");
			filename += commodity.getImageUrl().get(0);
			mailService.sendMail(filename,orders.get(pos).getMailOfbuyer());
			tradeService.sendedCommodity(order);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			request.getRequestDispatcher("/SellerServlet").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
