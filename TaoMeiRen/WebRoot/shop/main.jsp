<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "com.tao.model.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>淘美人商城</title> 
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">  
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="/TaoMeiRen/JS/myjs.js"></script>
	<!-- jQuery -->
		<script src="JS/lib/jquery.min.js"></script>
		<!-- Bootstrap -->
		<link href="JS/lib/css/bootstrap.min.css" rel="stylesheet">
		<script src="JS/lib/js/bootstrap.min.js"></script>
		<style type="text/css"> 
  </head>
  <style type="text/css">
 	#big_btn{
		background-color:white;
	}
	#big_btn:hover{
		color:#FF8000;
		background:#ECFFFF;	
	}
	body{
	}
  </style>
  <body style="background-color:#FFFFCC">
  <%
  User user = (User)session.getAttribute("sessionUser");
  %>
  <c:choose>
  	<c:when test="${sessionScope.sessionUser == null }">  
      <div align = "right">
      	<a href="<%=request.getContextPath() %>/user/login.jsp">登录</a>
      	<a href="<%=request.getContextPath() %>/user/regist.jsp">注册</a>
  	  </div>     
   </c:when>
   <c:otherwise>
   		<div align = "right">
      	<form id="individual_link"action="/TaoMeiRen/IndividualServlet"method = post></form>
      	<a  href="javascript:indivual()"><%=user.getEmail() %></a>
      	<a href="<%=request.getContextPath() %>/LogoutServlet">注销</a>
  	  </div>  
   </c:otherwise>
  </c:choose>
  
  <div align = center >
  <p align = center ><font size = 5 color = #005AB5>欢迎来到淘美人!<br/>(TaoMeiRen.com by yechen)</font>
  <p>
  </div>
  <table align = center>
    <tr>
    <td>
    <form id="big_btn"action = "/TaoMeiRen/DisplayServlet"method = "get">
    <input type = "hidden"name="display_type"value=1>
    <input style="margin-top:20;width:200px; height:250px;" type="submit" value="一口价专场" name="display">
    </form>
    </td>
    
    <td>
    <form  id="big_btn" action = "/TaoMeiRen/DisplayServlet"method = "get">
    <input type = "hidden"name="display_type"value=2>
    <input style="margin-top:20;width:200px; height:250px;" type="submit" value="团购专场" name="display">
    </form>
    </td>
    
    <td>
    <form id="big_btn" action = "/TaoMeiRen/DisplayServlet"method = "get">
    <input type = "hidden"name="display_type"value=3>
    <input style="margin-top:20;width:200px; height:250px" type="submit" value="竞拍专场" name="display">
    </form>
    </td>
  </tr>
  </table>
  </body>
</html>
