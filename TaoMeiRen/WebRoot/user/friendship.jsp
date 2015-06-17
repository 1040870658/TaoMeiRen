<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "com.tao.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
User user = (User)request.getSession().getAttribute("sessionUser");
if(user == null){
	response.sendRedirect(request.getContextPath()+"/user/login.jsp");
	return;
}
int row = 0;
int row_i = 0;
int row_g = 0;
String msg = (String)session.getAttribute("msg");
ArrayList<Group> groups = (ArrayList<Group>)session.getAttribute("groups");
ArrayList<User> invitors = (ArrayList<User>)session.getAttribute("invitor");
ArrayList<User> friends = (ArrayList<User>)session.getAttribute("friends");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(friends != null)
	row = friends.size();
if(invitors != null)
	row_i = invitors.size();
if(groups != null)
	row_g = groups.size();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的好友</title>
    
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
    <center><h1>好友中心</h1></center>
    <div>
   <table width="100%" border="0">
  
   <tr >
    <td >
   	您好,<a href="<%=request.getContextPath() %>/user/individual.jsp"><%=user.getEmail() %></a>~
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
   <td>
   <form action =" <%=request.getContextPath() %>/LogoutServlet"method = "post">
   		<input id="btn" type = "submit"value = "注销">
   </form>
   <form action =" <%=request.getContextPath() %>/InviteServlet"method = "post">
   		<input id="btn" type = "submit"value = "邀请">
   		<input type="text"name="invitation"placeholder="对方邮箱">
   		<p><font color = red><%=msg %></font>
   </form>
   
   <form action =" <%=request.getContextPath() %>/SetupGroupServlet"method = "post">
   		<input id="btn" type = "submit"value = "建群">
   		<input type="text"name="group_name"placeholder="小组名称">
   </form>
   </td>
   </tr>
</table>
</div>

<div style="float:left;margin-left:100">
<table align = center border = 0 cellspacing= 10>
	<caption><font size= 5 color = #2C3FFF>好友列表</font></caption>
	<tr bgcolor = #AD5A5A align = center>
		<td width = 100>
			好友名称
		</td>
	</tr>
	 <%
  	 for(int i = 0; i < row; i++ ){
  	 	User friend = friends.get(i);
 	%>
 	 <tr align = center>
 	 <td ><%=friend.getEmail() %></td>
     <%} %>
     
</table>
</div>
<div style="float:right;margin-right:300">
<table align = center border = 0 cellspacing= 10>
	<caption><font size= 5 color = #2C3FFF>小组列表</font></caption>
	<tr bgcolor = #AD5A5A align = center>
		<td width = 100>
			小组名称
		</td>
	</tr>
	 <%
  	 for(int i = 0; i < row_g; i++ ){
  	 	Group group = groups.get(i);
 	%>
 	 <tr align = center>
 	 <td ><%=group.getGroupName() %></td>
     <%} %>
     
</table>
</div>
<div style="float:right;margin-right:200">
<table align = center border = 0 cellspacing= 10>
	<caption><font size= 5 color = #CE1FFF>邀请列表</font></caption>
	<tr bgcolor = #007979 align = center>
		<td width = 100>
			好友名称
		</td>
		<td width = 100>
			操作
		</td>
	</tr>
	 <%
  	 for(int i = 0; i < row_i; i++ ){
  	 	User invitor = invitors.get(i);
 	%>
 	 <tr align = center>
 	 <td ><%=invitor.getEmail() %></td>
 	 <td > <form style = "float:left"action="AcceptServlet">
   		  <input type = "submit" value="接受邀请"/>
   		  <input type = "hidden" value="<%=i %>"name="position"/>
   		  <input type = "hidden" value="true"name="invite"/>
   	 </form>
   	 <form style = "float:right"action="AcceptServlet">
   		 <input type = "hidden" value="<%=i %>"name="position"/>
   	 	 <input type = "hidden" name ="invite" value="false">
    	 <input type = "submit" value="拒绝邀请"/>
     </form>
     </td>
     <%} %>
     
</table>
</div>
  </body>
</html>
