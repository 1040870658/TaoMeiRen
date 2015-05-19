package com.tao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.service.CommodityService;

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
		System.out.println(user == null);
		Commodity commodity = CommonUtils.toBean(request.getParameterMap(), Commodity.class);
		String dealtype = (String) request.getParameter("dealtype");
		System.out.println(dealtype);
		commodity.setType(Commodity.TYPE.get(dealtype));
		CommodityService commodityService = new CommodityService();
		if(commodityService.registerCommodity(user, commodity)){
			request.setAttribute("msg", "success");
		}
		else{
			request.setAttribute("msg", "failed");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
