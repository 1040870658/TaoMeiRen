<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "com.tao.model.*" %>
<%
User user = (User)request.getSession().getAttribute("sessionUser");
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
    
    <title>个人中心</title>
    
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
    <a href="shop.jsp"style="text-align:right">去商城</a>
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
   
  <tr>
    <td><form action = "/TaoMeiRen/BuyerServlet"><input style="margin-top:20;width:200px; height:250px;" type="submit" value="我是买家" name="bt"></form></td>
    <td><form action = "/TaoMeiRen/SellerServlet"><input style="margin-top:20;width:200px; height:250px;" type="submit" value="我是卖家" name="bt"></form></td>
    <td><form action = "/TaoMeiRen/FriendServlet"><input style="margin-top:20;width:200px; height:250px;" type="submit" value="我的好友" name="bt"></form></td>
  </tr>
</table>
  </body>
</html>
