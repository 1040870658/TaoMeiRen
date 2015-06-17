<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "com.tao.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int row = 0;
ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(orders != null)
	row = orders.size();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我是买家</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet"media="screen"href="user/css/btncss.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background-color:#FFFFCC">
    <center><h1>管理员中心</h1></center>
    <div>
   <table width="100%" border="0"> 
   <tr>
   <td>
   <form action =" <%=request.getContextPath() %>/LogoutServlet"method = "post"><input id="btn" type = "submit"value = "注销"> </form>
   </td>
   </tr>
</table>
</div>

<table align = center border = 0 cellspacing= 10>
	<caption><font size= 5 color = olive>近期订单</font></caption>
	<tr bgcolor = #007979 align = center>
		<td width = 100>
			商品序号
		</td>
		<td width = 100>
			数量
		</td>
		<td width = 100>
			价格
		</td>
		<td width = 100>
			卖家
		</td>
		<td width = 100>
			买家
		</td>
	</tr>
	 <%
  	 for(int i = 0; i < row; i++ ){
  	 	Order order = orders.get(i);
 	%>
 	 <tr align = center>
 	 <td ><%=order.getId() %></td>
     <td ><%=order.getItemNum() %></td>
   	 <td ><%=order.getDeposit() %></td>
   	 <td ><%=order.getMailOfseller() %></td>
     <td ><%=order.getMailOfbuyer() %> </td>
     
     <%} %>
     
</table>
  </body>
</html>
