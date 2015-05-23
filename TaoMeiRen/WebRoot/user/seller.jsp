<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "com.tao.model.*" %>
<%
int fix_rows = 0;
int auction_rows = 0;
int collective_rows = 0;
int fixes_rows = 0;
int collections_rows = 0;
int auctions_rows = 0;
User user = (User)request.getSession().getAttribute("sessionUser");
ArrayList<Order> fix_orders = (ArrayList<Order>)request.getAttribute("fix_orders");
ArrayList<Order> auction_orders = (ArrayList<Order>)request.getAttribute("auction_orders");
ArrayList<Order> collection_orders = (ArrayList<Order>)request.getAttribute("collection_orders");
ArrayList<Commodity> fixes= (ArrayList<Commodity>)request.getAttribute("fixes");
ArrayList<Commodity> auctions = (ArrayList<Commodity>)request.getAttribute("auctions");
ArrayList<Commodity> collections = (ArrayList<Commodity>)request.getAttribute("collections");
Map<Integer,String> commodityName = (HashMap)request.getAttribute("commodityNames");
if(fix_orders != null)
	fix_rows = fix_orders.size();
if(auction_orders != null)
	auction_rows = auction_orders.size();
if(collection_orders != null)	
	collective_rows =collection_orders.size();
if(null == user){
	response.sendRedirect(request.getContextPath()+"/user/login.jsp");
}
if(fixes != null)
	fixes_rows = fixes.size();
if(auctions != null)
	auctions_rows = auctions.size();
if(collections != null)	
	collections_rows =collections.size();
if(null == user){
	response.sendRedirect(request.getContextPath()+"/user/login.jsp");
}
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我是卖家</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table width="100%" border="0">
   
   <tr>
    <td>
   	您好,<%=user.getEmail() %>~!
    </td>
    <td>
    <a href="user/register_commodity.jsp"style="text-align:right">登记商品</a>
    </td>
   </tr>
   
   <tr>
    <td>
           您的余额是：<%=user.getAccount() %>
    </td>
   <tr>
   
   <td>
        您的信用是：<%=user.getBuyerCredit() %>
   </td>
   </tr>
</table>

<table align = center border = 0>
	<caption><font size= 5 color = olive>待发货</font></caption>
	<tr cellspacing = 5>
	<th colspan = 4 width = 300 >一口价</th>
	<th colspan = 4 width = 300>团购</th>
	<th colspan = 4 width = 300>竞拍</th>
   </tr>
   
   <tr bgcolor = olive align = center>
   <td >商品</td>
   <td >数量</td>
   <td >价格</td>
   <td >发货</td>
   <td >商品</td>
   <td >数量</td>
   <td >价格</td>
   <td >发货</td>
   <td >商品</td>
   <td >数量</td>
   <td >价格</td>
   <td >发货</td>
   </tr>
   <tr></tr><br/>
     <%
  	 for(int i = 0; i < fix_rows; i++ ){
  	 	Order order = fix_orders.get(i);
 	%>
 	 <tr>
 	 <td ><%=commodityName.get(order.getCommodityID()) %></td>
     <td ><%=order.getItemNum() %></td>
   	 <td ><%=order.getDeposit() %></td>
     <td >
     
     <form action="/TaoMeiRen/TradeServlet">
     <input type = "submit" value="发货"/name="send_email">
     <input type = "submit" value="取消"/name="cacel_trade">
     <input type = "submit" value="详情"/name="select_detail">
     </form>
     
     </td>
     
     <%} %>
     
     <% for(int i = 0; i < collective_rows; i++ ){
  	 	Order order = auction_orders.get(i);
  	 %>
     <td ><%=commodityName.get(order.getCommodityID()) %></td>
     <td ><%=order.getItemNum() %></td>
   	 <td ><%=order.getDeposit() %></td>
     <td >
     
     <form action="/TaoMeiRen/TradeServlet">
     <input type = "submit" value="发货"/name="send_email">
     <input type = "submit" value="取消"/name="cacel_trade">
     <input type = "submit" value="详情"/name="select_detail">
     </form>
     
     </td>
     <%} %>
     
     <% for(int i = 0; i <auction_rows; i++ ){
  	 	Order order = auction_orders.get(i);%>
     <td ><%=commodityName.get(order.getCommodityID()) %></td>
     <td ><%=order.getItemNum() %></td>
   	 <td ><%=order.getDeposit() %></td>
     <td >
     
     <form action="/TaoMeiRen/TradeServlet">
     <input type = "submit" value="发货"/name="send_email">
     <input type = "submit" value="取消"/name="cacel_trade">
     <input type = "submit" value="详情"/name="select_detail">
     </form>
     
     </td>
     </tr>
 	<%
 	  }
	%>
	
</table>

<table align = center border = 0>
	<caption><font size= 5 color = cyan>剩余库存</font></caption>
	<tr>
	<th colspan = 2 width = 300 >一口价</th>
	<th colspan = 2 width = 300>团购</th>
	<th colspan = 2 width = 300>竞拍</th>
   </tr>
   
   <tr bgcolor = olive align = center>
   <td >商品</td>
   <td >数量</td>
   <td >商品</td>
   <td >数量</td>
   <td >商品</td>
   <td >数量</td>
   </tr> 
    
   <tr></tr><br/> 
   <%
  	 for(int i = 0; i < fixes_rows; i++ ){
  	 	Commodity commodity = fixes.get(i);
 	%>
 	 <tr align = center>
 	 <td ><%=commodity.getName()%></td>
     <td ><%=commodity.getStock() %></td>
    <%} %>
    
    <%
  	 for(int i = 0; i < collections_rows; i++ ){
  	 	Commodity commodity = auctions.get(i);
 	%>
 	 <td ><%=commodity.getName()%></td>
     <td ><%=commodity.getStock() %></td>
    <%} %>
    
    <%
  	 for(int i = 0; i < auctions_rows; i++ ){
  	 	Commodity commodity = auctions.get(i);
 	%>
 	 <td ><%=commodity.getName()%></td>
     <td ><%=commodity.getStock() %></td>
    <%} %>
    </tr>
</table>
  </body>
</html>
