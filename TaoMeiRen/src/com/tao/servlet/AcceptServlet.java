package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.User;
import com.tao.service.FriendService;

public class AcceptServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		User host = (User) request.getSession().getAttribute("sessionUser");
		if (null == host || request.getSession() == null) {
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			return;
		}
		FriendService friendService = new FriendService();
		ArrayList<User> invitors = (ArrayList<User>)request.getSession().getAttribute("invitor");
		int pos = Integer.parseInt(request.getParameter("position"));
		String isAccepted = request.getParameter("invite");
		User invitor = invitors.get(pos);
		if(isAccepted.equals("true")){
			friendService.acceptInvitation(host,invitor );
		}
		else{
			friendService.rejectInvitation(host,invitor);
		}
		request.getRequestDispatcher("FriendServlet").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
