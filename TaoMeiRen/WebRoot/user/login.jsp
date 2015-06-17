<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import ="com.tao.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String msg = (String)session.getAttribute("validate");
if (msg == null)
	msg = "";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Login</title>
    <link rel="stylesheet"media="screen"href="css/logcss.css"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background-color:#FFFFCC">
  <div id="apDiv1">
  
<h1><font color = #408080>登录</font></h1>
<%--${pageContext.request.contextPath }/RegistServlet --%>
<form action="<c:url value='/LoginServlet'/>" method="post">
<label for = "name">Email:</label>
    	<input type="text" id="name" name="email"type="text" name="email" value="${user.email }"
    	placeholder="Your email."/>
    	</p>
    	<p>
    	<label for = "psd">Password:</label>
    	<input type="password" id="psd" name="password",value="${user.password }"
    	placeholder="Your password." width="400"height="0"/>
        </p>
        <input id="btn"type="submit"value="submit"/>
        <input id="type"value="0"type="hidden"name="log_type"/>
        <select onChange = "login()"id="log_type">
        	<option value = 0>普通用户</option>
        	<option value = 1>管理员</option>
        </select>
        
</form>
<p ><font size = "3"color = red><%=msg %></font></p>
</div>
  </body>
  <script>
	function login(){
		document.getElementById("type").value = document.getElementById("log_type").value;
	}
</script>
</html>
