<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "com.tao.model.*" %>
<%
//String email = (String)request.getAttribute("email");
User user=(User)request.getSession().getAttribute("sessionUser");
String email=user.getEmail();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Hello,<%=email%></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="refresh"content="2;URL=/TaoMeiRen/user/individual.jsp">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor = RGB(255,255,204)>
  <center>
  <div align = center style = "margin-top : 20%">
    <font face="幼圆" size = 7 >Welcome ,<%=email%></font>
  </div>
  </center>
  </body>
</html>
