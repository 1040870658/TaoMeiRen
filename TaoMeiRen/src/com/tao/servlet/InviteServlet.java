package com.tao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.User;
import com.tao.service.FriendService;
import com.tao.service.UserService;

public class InviteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User host = (User) request.getSession().getAttribute("sessionUser");
		if (null == host|| request.getSession() == null) {
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			return;
		}
		boolean success = false;
		String msg = "";
		FriendService friendService = new FriendService();
		UserService userService = new UserService();
		String guestString = request.getParameter("invitation");
		User guest = userService.queryUser(guestString);
		if(guest == null){
			msg="用户不存在";
			request.getSession().setAttribute("msg", msg);
			request.getRequestDispatcher("user/friendship.jsp").forward(request,response);
			return;
		}
		friendService.invite(host, guest);
		request.getSession().setAttribute("msg", msg);
		request.getRequestDispatcher("user/friendship.jsp").forward(request,response);
		return;
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
