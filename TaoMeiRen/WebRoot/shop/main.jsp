<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>淘美人商城</title>
    
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
  <div>
  <p align = center ><font color = #005AB5>欢迎来到淘美人<br/>(TaoMeiRen.com by yechen)</font>
  <p>
  </div>
  <table align = center>
    <tr>
    <td><form action = "/TaoMeiRen/DisplayServlet"method = "get"><input style="margin-top:20;width:200px; height:250px;" type="submit" value="一口价专场" name="display"></form></td>
    <td><form action = "/TaoMeiRen/DisplayServlet"method = "get"><input style="margin-top:20;width:200px; height:250px;" type="submit" value="团购专场" name="display"></form></td>
    <td><form action = "/TaoMeiRen/DisplayServlet"method = "get"><input style="margin-top:20;width:200px; height:250px;" type="submit" value="竞拍专场" name="display"></form></td>
  </tr>
  </table>
  </body>
</html>
