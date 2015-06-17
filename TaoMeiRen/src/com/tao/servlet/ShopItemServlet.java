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
import com.tao.service.AuctionService;
import com.tao.service.CollectionService;
import com.tao.service.CommentService;

public class ShopItemServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		CommentService commentService = new CommentService();
		int pos = Integer.parseInt(request.getParameter("position"));
		ArrayList<PersonalComment>personalComments = new ArrayList<PersonalComment>(); 
		ArrayList<Commodity>commodities = (ArrayList<Commodity>) request.getServletContext().getAttribute("all_concrete_commodities");
		Commodity commodity = commodities.get(pos);
		personalComments = commentService.queryPersonalComments(commodity);
		request.setAttribute("detail_commodity", commodity);
		request.setAttribute("personal_comments", personalComments);
		request.getSession().setAttribute("personal_comments", personalComments);
		switch(commodity.getType()){
			case Commodity.FIX:doFix(request, response);break;
			case Commodity.COLLECTIVE:doCollection(request, response, commodity);break;
			case Commodity.AUCTION:doAuction(request, response, commodity);break;
			default:doFix(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
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
