package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Group;
import com.tao.model.User;
import com.tao.service.FriendService;
import com.tao.service.GroupService;

public class FriendServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("sessionUser");
		if (null == user || request.getSession() == null) {
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			return;
		}
		GroupService groupService = new GroupService();
		FriendService friendService = new FriendService();
		ArrayList<User>friends = friendService.queryFriend(user);
		ArrayList<User>invitors = friendService.queryInvitation(user);
		ArrayList<Group>groups = groupService.displayGroups(user);
		request.getSession().setAttribute("friends", friends);
		request.getSession().setAttribute("invitor", invitors);
		request.getSession().setAttribute("groups", groups);
		System.out.println(invitors.size());
		request.getSession().setAttribute("msg", "");
		response.sendRedirect("/TaoMeiRen/user/friendship.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
