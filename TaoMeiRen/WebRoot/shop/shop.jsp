<%@page import="com.tao.model.Commodity"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tao.model.*"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList<Commodity> commodities = (ArrayList<Commodity>)request.getServletContext().getAttribute("all_concrete_commodities");
int index = (Integer)request.getServletContext().getAttribute("index");
int total_size = (Integer)request.getServletContext().getAttribute("size");
index++;
int i = 0;
int size ;
String imageUrl = "";
if(commodities != null ){
	 imageUrl = commodities.get(0).getImageUrl().get(0);
	 size = commodities.size();
}
else{
	size = 0;
	commodities = new ArrayList<Commodity>();
	commodities.add(new Commodity());
	commodities.get(0).setPrice(0);
	commodities.get(0).setName("");
	imageUrl = "";
}
%>


<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<script src="/TaoMeiRen/JS/myjs.js"></script>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品</title>
		<!-- jQuery -->
		<script src="JS/lib/jquery.min.js"></script>
		<!-- Bootstrap -->
		<link href="JS/lib/css/bootstrap.min.css" rel="stylesheet">
		<script src="JS/lib/js/bootstrap.min.js"></script>
		<script src="/TaoMeiRen/JS/myjs.js"></script>
		<style type="text/css">
.modal-dialog {
	height: 220px;
	width: 350px;
}

.thumbnail {
	height: 265px;
	width: 220px;
}

.thumbnail>a>img {
	height: 110px;
	width: 130px;
}

.caption {
	background-color: rgba(255, 255, 153, 0.3);
}
</style>
	</head>

	<body style="background-color: #FFFFCC">

		<%
  User user = (User)session.getAttribute("sessionUser");
  %>
		<c:choose>
			<c:when test="${sessionScope.sessionUser == null }">
				<div align="right">
					<a href="<%=request.getContextPath() %>/user/login.jsp">登录</a>
					<a href="<%=request.getContextPath() %>/user/regist.jsp">注册</a>
				</div>
			</c:when>
			<c:otherwise>
				<div align="right">
					<form id="individual_link" action="/TaoMeiRen/IndividualServlet"
						method=post></form>
					<a href="javascript:indivual()"><%=user.getEmail() %></a>
					<a href="<%=request.getContextPath() %>/LogoutServlet">注销</a>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="container">
			<div align=center>
				<h1 style="align: center">
					<font face="verdana" color=#B8860B>淘美人商城</font>
				</h1>
			</div>
			<!--大屏轮播图-->
			<div class="row">
				<div class="col-md-12">
					<div class="carousel slide" id="carousel-1">
						<ol class="carousel-indicators">
							<li class="active" data-slide-to="0" data-target="#carousel-1">
							</li>
							<li data-slide-to="1" data-target="#carousel-1">
							</li>
							<li data-slide-to="2" data-target="#carousel-1">
							</li>
						</ol>
						<div class="carousel-inner" align="center">
							<div class="item active">
								<img src="user/image/title1.jpg" />
							</div>
							<div class="item">
								<img src="user/image/title2.jpg" />
							</div>
							<div class="item">
								<img src="user/image/title3.jpg" />
							</div>
						</div>
						<a data-slide="prev" href="#carousel-1"
							class="left carousel-control">‹</a>
						<a data-slide="next" href="#carousel-1"
							class="right carousel-control">›</a>
					</div>
				</div>
			</div>
			<br>
			<form class="navbar-form navbar-left" role="search"
				style="padding-left: 70%"action="SearchServlet"method="post">
				<div class="form-group">
					<input type="text" class="form-control" width="300px"
						placeholder="输入商品名称"name="search_name">
				</div>
				<input type="submit" class="btn btn-primary"value="搜索">
				<input type="hidden" value="<%=commodities.get(0).getType() %>"
					name="display_type">
			</form>

			<div class="row" style="margin-top: 5%" id="r1">
				<div class="col-md-12">
					<hr>
					<h3 align="center"></h3>
					<div class="col-md-3" id="c1" style="DISPLAY: none">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">
							</a>
							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
		
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c2">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c3">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
		
	%>
					<div class="col-md-3" id="c4">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
				</div>
				<%	
    
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
			</div>

			<!--海产类-->
			<div class="row" id="r2">
				<div class="col-md-12">
					<hr>
					<h3 align="center"></h3>
					<div class="col-md-3" id="c5">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
						<%	
        
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
		%>
					</div>

					<div class="col-md-3" id="c6">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c7">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c8">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
			<div class="row" id="r3">
				<div class="col-md-12">
					<hr>
					<h3 align="center"></h3>
					<div class="col-md-3" id="c9">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c10">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c11">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c12">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
			<!--水果类-->
			<div class="row" id="r4">
				<div class="col-md-12">
					<hr>
					<h3 align="center"></h3>
					<div class="col-md-3" id="c13">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c14">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c15">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
					<%	
		if(i + 1 < size){
			i ++;
			imageUrl = commodities.get(i).getImageUrl().get(0);
		}
	%>
					<div class="col-md-3" id="c16">
						<div class="thumbnail">

							<img src="<%=imageUrl %>">

							<div class="caption" align="center">
								<h3><%=commodities.get(i).getName()%></h3>
								<p>
									<strong>价格：</strong><%=commodities.get(i).getPrice()%>元
								</p>
								<p>
								<form action="ShopItemServlet">
									<input type="hidden" id="position" name="position"
										value="<%=i %>">
									<input class="btn btn-primary" id="submit_detail" type="submit"
										value="购买">
								</form>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<form id="turn_page" action="DisplayServlet" method=post>
				<input type="hidden" value="1" name="page" id="page">
				<input type="hidden" value="<%=commodities.get(0).getType() %>"
					name="display_type">
			</form>
			<center>
				<a href="shop/main.jsp"><button class="btn btn-danger">
						返回
					</button>
				</a>
			</center>

		</div>
		<script type="text/javascript">
	window.onload=hideDiv(<%=size%>);
	window.onload=turnpage(<%=total_size%>);
</script>
	</body>
</html>