<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String msg = (String)session.getAttribute("msg");
if (msg == null)
	msg = "";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Register</title>
    <link rel="stylesheet"media="screen"href="css/mangerlogcss.css"/>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	/*
	如果一个表单项的name和<img>的id相同，那么可能会出问题！一般只有IE出问题！
	*/
	function _change() {
		/*
		1. 获取<img>元素
		*/
		var ele = document.getElementById("vCode");
		ele.src = "<c:url value='/VerifyCodeServlet'/>?xxx=" + new Date().getTime();
		
	}
</script>
  </head>
  
  <body style="background-color:#FFFFCC">


<%--${pageContext.request.contextPath }/RegistServlet --%>
<div id="apDiv1">
<h2>注册</h2>
  <form id ="form1" action="<c:url value='/RegistServlet'/>" method="post">
		<p>
    	<label for = "name">Email:</label>
    	<input type="text" id="name" name="email"value="${user.email}"
    	placeholder="Your name."/>
    	</p>
    	<p>
    	<label for = "psd">Password:</label>
    	<input type="password" id="psd" name="password"value="${user.password}"
    	placeholder="Your password." width="400"height="0"/>
        </p>
        <p>
        <label>VerrifyCode:</label>
        <input type="text" name="verifyCode" value="${verifyCode }" size="3"/>
        <img id="vCode" src="<c:url value='/VerifyCodeServlet'/>" border="2"/>
        <a href="javascript:_change()">换一张</a>${errors.verifyCode }<br/>
        </p>
        <p>
        <input id="btn"type="submit"value="注册"/>
        <p style="color: red; ">${msg }</p>
        </p>
   	  
  </form>
</div>


  </body>
</html>
