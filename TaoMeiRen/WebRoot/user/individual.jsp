<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.tao.model.*"%>
<%
	User user = (User) request.getSession().getAttribute("sessionUser");
	if (user == null) {
		response.sendRedirect(request.getContextPath()
				+ "/user/login.jsp");
		return;
	}
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<link rel="stylesheet"media="screen"href="user/css/btncss.css"/>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body style="background-color:#FFFFCC">
	<center><h1>个人中心</h1></center>
		<div align=center>

			<table width="100%" border="0">

				<tr>
					<td>
						您好,<%=user.getEmail()%>~!
					</td>
				</tr>

				<tr>
					<td>
						您的余额是：<%=user.getAccount()%>
					</td>
				<tr>

					<td>
						您的信用是：<%=user.getBuyerCredit()%>
					</td>
				</tr>

				<tr>
					<td>
						<form action=" <%=request.getContextPath()%>/LogoutServlet"
							method="post">
							<input id="btn" type="button" value="去商城"onclick = "window.location='shop/main.jsp'">
							<input id="btn" type="submit" value="注销">
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<form action="/TaoMeiRen/BuyerServlet">
							<input id="big_btn"
								type="submit" value="我要买" >
						</form>
					</td>
					<td>
						<form action="/TaoMeiRen/FriendServlet">
							<input id="big_btn"
								type="submit" value="我的好友">
						</form>
					</td>
					<td>
						<form action="/TaoMeiRen/SellerServlet">
							<input id="big_btn"
								type="submit" value="我要卖" >
						</form>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
