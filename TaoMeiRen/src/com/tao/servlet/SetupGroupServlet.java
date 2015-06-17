package com.tao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Group;
import com.tao.model.User;
import com.tao.service.GroupService;

public class SetupGroupServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("sessionUser");
		if (null == user || request.getSession() == null) {
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			return;
		}
		String name = request.getParameter("group_name");
		Group group = new Group();
		group.setMaster(user.getEmail());
		group.setMemberNum(1);
		group.setGroupName(name);
		group.setGroupID(0);
		GroupService groupService = new GroupService();
		groupService.setUp(group);
		request.getRequestDispatcher("FriendServlet").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
