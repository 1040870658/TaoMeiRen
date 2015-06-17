<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tao.model.*"%>
<%
	int fix_rows = 0;
	int auction_rows = 0;
	int collective_rows = 0;
	int fixes_rows = 0;
	int collections_rows = 0;
	int auctions_rows = 0;
	User user = (User) request.getSession().getAttribute("sessionUser");
	if (user == null) {
		response.sendRedirect(request.getContextPath()
				+ "/user/login.jsp");
		return;
	}
	ArrayList<Order> orders = (ArrayList<Order>) session
			.getAttribute("orders");
	ArrayList<Commodity> commodities = (ArrayList<Commodity>) session
			.getAttribute("commodities");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>我是卖家</title>
		<!-- jQuery -->
    	<script src="/TaoMeiRen/JS/myjs.js"></script>
    	
		<link rel="stylesheet" media="screen" href="user/css/btncss.css" />
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
		<center>
			<h1>
				卖家中心
			</h1>
		</center>
		<table width="100%" border="0">

			<tr>
				<td>
					您好，
					<a href="<%=request.getContextPath()%>/user/individual.jsp"><%=user.getEmail()%></a>~
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
						<input type="button" id="btn"
							onclick="window.location = 'user/register_commodity.jsp'"
							value="出售" />
						<input id="btn" type="submit" value="注销">
					</form>
				</td>
			</tr>
		</table>
<div style="float: right;  width: 30%;margin-right:15%">
		<table align=center border=0>
			<caption>
				<font size=5 color=olive>待发货</font>
				<br />
				<select id = "order_type"onchange="autoSubmitSelect()">
					<option value="1">
						一口价
					</option>
					<option value="2">
						团购
					</option>
					<option value="3">
						竞拍
					</option>
				</select>
				<br />
			</caption>

			<tr bgcolor=#B87070 align=center>
				<td width=100>
					订单号
				</td>
				<td width=100>
					买家
				</td>
				<td width=100>
					交易额
				</td>
				<td width = 100>
					发货
				</td>
			</tr>
			<tr></tr>
			<%int i = 0; %>
			<c:forEach items="${orders}" var="c">
				<tr align=center>
					<td>
						<c:out value="${c.getId()}"></c:out>
					</td>
					<td>
						<c:out value="${c.getMailOfbuyer() }"></c:out>
					</td>
					<td>
						<c:out value="${c.getDeposit() }"></c:out>
					</td>
					<td>
					<div align = center>
						<form style="float:left" action="SendServlet">
							<input type = "hidden" id = "position"name="position"value = "<%=i %>" >
							<input onclick = "alert('发货成功')" type="submit" value="发货" /name="send_email">
						</form>
						<form style="float:right" action="OrderDetailServlet">
							<input type = "hidden" name ="order_detail_commodityID" value="<%=orders.get(i).getCommodityID() %>">
							<input type="submit" value="详情" /name="select_detail">
						</form>
					</div>
					</td>
				</tr>
				<%i++; %>
			</c:forEach>


		</table>

</div>
<div style="float: left; width: 30% ;margin-left : 15%;">
		<table align=center border=0>
			<caption>
				<font size=5 color=cyan>剩余库存</font>
				<br />
				<select id ="stock_type"onchange="autoSubmitSelect()">
					<option value="1">
						一口价
					</option>
					<option value="2">
						团购
					</option>
					<option value="3">
						竞拍
					</option>
				</select>
				<br />
			</caption>

			<tr bgcolor=#408080 align=center>
				<td width=70>
					商品
				</td>
				<td width=70>
					数量
				</td>
				<td width=70>
					价格
				</td>
				<td width=100 colspan = 2>
					详情
				</td>
			</tr>

			<tr></tr>
			<%i = 0; %>
			<c:forEach items="${commodities}" var="c">
				<tr align=center>
					<td>
						<c:out value="${c.getName()}"></c:out>
					</td>
					<td>
						<c:out value="${c.getStock() }"></c:out>
					</td>
					<td>
						<c:out value="${c.getPrice() }"></c:out>
					</td>
					<td>
						<form style = "float:left"action="SellerDetailServlet">
							<input type = "hidden" id = "position"name="position"value = "<%=i %>" >
							<input id = "submit_detail"type="submit" value="查看">
						</form>
						<form style = "float:right" action="DropServlet">
							<input type = "hidden" id = "position"name="position"value = "<%=i %>" >
							<input id = "submit_detail"type="submit" value="下架">
						</form>
					</td>
				</tr>
				<%i++; %>
			</c:forEach>

		</table>
		</div>
		<script>
		 
		</script>
		<div style="clear: both ;"align = center>
		<form id = "refresh_data"action = "/TaoMeiRen/SellerServlet"method="post">
			<input type = "hidden"name="order_type"id="order_detail_type">
			<input type = "hidden"name="commodity_type"id="commodity_detail_type">
		</form>
		</div>
		<script type="text/javascript">
    		window.onload=changeType(<%=request.getParameter("order_type")%>,<%=request.getParameter("commodity_type")%>);
    	</script>
	</body>
</html>
