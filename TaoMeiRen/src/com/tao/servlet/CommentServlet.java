package com.tao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Auction;
import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.model.PersonalComment;
import com.tao.model.User;
import com.tao.service.AuctionService;
import com.tao.service.CollectionService;
import com.tao.service.CommentService;
import com.tao.service.CommodityService;

public class CommentServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(null == user){
			response.sendRedirect("user/login.jsp");
			return;
		}
		CommentService commentService = new CommentService();
		ArrayList<PersonalComment> personalComments = new ArrayList<PersonalComment>();
		personalComments = (ArrayList<PersonalComment>) request.getSession().getAttribute("personal_comments");
		Commodity commodity = (Commodity) request.getSession().getAttribute("commodityOfcomment");
		String personal_pointString = request.getParameter("personal_point");
		String[] convert = personal_pointString.split(" ");
		String personal_comment = request.getParameter("personal_comment");
		int personal_point = Integer.parseInt(convert[0]);
		PersonalComment personalComment = new PersonalComment(0, commodity.getId(), personal_point, personal_comment, user.getEmail());
		commentService.addPersonalComment(personalComment);
		if(personalComments == null)
			personalComments = new ArrayList<PersonalComment>();
		personalComments.add(personalComment);
		request.setAttribute("personal_comments", personalComments);
		request.setAttribute("detail_commodity", commodity);
		switch(commodity.getType()){
		case Commodity.FIX:doFix(request, response);break;
		case Commodity.COLLECTIVE:doCollection(request, response, commodity);break;
		case Commodity.AUCTION:doAuction(request, response, commodity);break;
		default:doFix(request, response);
	}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void doFix(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		request.getRequestDispatcher("shop/detail.jsp").forward(request, response);
	}
	private void doCollection(HttpServletRequest request, HttpServletResponse response,Commodity commodity)throws ServletException,IOException{
		CollectionService collectionService = new CollectionService();
		Collection collection = collectionService.queryCollection(commodity);
		request.setAttribute("detail_collection", collection);
		request.getRequestDispatcher("shop/collection_detail.jsp").forward(request, response);
	}
	private void doAuction(HttpServletRequest request, HttpServletResponse response,Commodity commodity)throws ServletException,IOException{
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.queryAuction(commodity);
		request.setAttribute("detail_auction", auction);
		request.getRequestDispatcher("shop/auction_detail.jsp").forward(request, response);
	}
	
}
