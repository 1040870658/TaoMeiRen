<%@page import="com.tao.model.PersonalComment"%>
<%@page import="com.tao.model.Commodity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
Object object;
if(session != null)
	object = session.getAttribute("personal_comments");
else
	object = request.getAttribute("personal_comments");
ArrayList<PersonalComment> personalComments;
if(object != null){
	personalComments = (ArrayList<PersonalComment>)object;
}
else{
	personalComments = new ArrayList<PersonalComment>();
}
Commodity commodity = (Commodity)request.getAttribute("detail_commodity");
session.setAttribute("commodityOfcomment",commodity);
session.setAttribute("personal_comments",personalComments);
String[] type = {"null","一口价","团购","竞拍"};
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
String serverPath = session.getServletContext().getRealPath("/").replace("\\", "/");
String image =commodity.getImageUrl().get(0);
session.setAttribute("commodityOfOrder",commodity);
String tips = (String)request.getAttribute("tips");

if(tips == null)
	tips = "";
out.print(".");
%>

<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
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
.img-thumbnail {
	height: 210px;
	width: 300px;
}

.thumbnail {
	height: 250px;
	width: 180px;
}

.modal-dialog {
	height: 200px;
	width: 350px;
}

h3#itemName {
	font-weight: 900;
	padding-left: 100px;
}

.thumbnail>img {
	width: 160px;
	height: 110px;
}

.thumbnail#userComment {
	width: 100%;
	height: auto;
	background-color: 
}
</style>
	</head>
	<body style="background-color: #FFFFCC">
		<center>
			<h1>
				商品详情
			</h1>
		</center>
		<div class="container">


			<!--modal评价-->
			<div class="modal fade " id="comment" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="padding-top: 150px" height="600px">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel" align="center">
								填写评价
							</h4>
						</div>
						<form class="form-horizontal"action="CommentServlet"method =post>
						<div class="modal-body">
								<div class="row">
									<div class="col-md-1">
									</div>
									<div class="col-md-10">
										<input type="text" class="form-control"
											placeholder="Text input"name="personal_comment">
									</div>
									<div class="col-md-1">
									</div>
								</div>
						</div>
						<div class="modal-footer">
							<div align="center">
								
									<input type = "hidden"name="personal_point"id="personal_point"value="5">
									<button type="submit" class="btn btn-primary">
										提交评论
									</button>
									<select>
										<option>个人</option>
										<option>小组</option>
									</select>
									<select id = "point"onChange="commentPoint()">
										<option>5 分</option>
										<option>4 分</option>
										<option>3 分</option>
										<option>2 分</option>
										<option>1 分</option>
									</select>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--项目内容-->
		<div class="container">
			<div class="row">
				<div class="col-md-2">
				</div>
				<div class="col-md-3" style="padding-top: 70px; padding-left: 40px">
					<img class="img-circle" src="<%=image %>"
						style="width: 220px; height: 220px">
					<div style="padding-top: 10px; padding-left: 85px;">
						<a href="#comment" class="navbar-link" data-toggle="modal"><button
								class="btn btn-info">
								评价
							</button>
						</a>

					</div>
				</div>
				<div class="col-md-4">
					<h3 id="itemName"><%=commodity.getName() %></h3>
					<hr>
					<div>
						<table class="table table-bordered table-hover">
							<tr>
								<td>
									<p align="center">
										类别
									</p>
								</td>
								<td>
									<span class="text-muted" style="font-size: 20px"><%=type[commodity.getType()] %></span>
								</td>
							</tr>
							<tr>
								<td>
									<p align="center">
										价格
									</p>
								</td>
								<td>
									<span class="text-danger" style="font-size: 20px">￥<%=commodity.getPrice() %></span>
								</td>
							</tr>
							<tr>
								<td>
									<p align="center">
										库存
									</p>
								</td>
								<td>
									<span class="text-muted" style="font-size: 20px"><%=commodity.getStock() %></span>
								</td>
							</tr>
							<tr>
								<td>
									<p align="center">
										描述
									</p>
								</td>
								<td>
									<span class="text-muted" style="font-size: 20px"><%=commodity.getDescription() %></span>
								</td>
							</tr>
						</table>
						<form action="<%=request.getContextPath() %>/TradeServlet" method="post">
							<span style="padding-top: 30px; padding-left: 20px">购买数量&nbsp;&nbsp;&nbsp;</span>
							<button type="button" class="btn btn-Success"
								onclick="numberMinus()">
								<span class="glyphicon glyphicon-minus"></span>
							</button>
							<input type="text" id="goodNumber" name="item_number"
								style="width: 45px; height: 45px; text-align: center" value="0">
							<button type="button" class="btn btn-Success"
								onclick="numberPlus()">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
							<input type="submit" class="btn btn-danger"onclick = "buy('<%=session.getAttribute("sessionUser") != null  %>')" value="购买">
						</form>

					</div>
					<div class="col-md-3">
					</div>
				</div>
			</div>
		</div>


		<!--用户评论-->
		<div class="container">
			<div class="row">
				<div class="col-md-2">
				</div>
				<div class="col-md-8">
					<h4>
						用户评论
					</h4>
					<div class="thumbnail" id="userComment">
					<%for(PersonalComment comment : personalComments){ %>
						<h5><%=comment.getComment() %></h5>
						<p style="font-size: 5px"></p>
						<font color= pink><%=comment.getPoint() %> 分</font>
						<blockquote>
							<p style="font-size: 15px"></p>
							<footer><%=comment.getEmail() %></footer>
						</blockquote>
					<%} %>
					</div>
					
				</div>
				<div class="col-md-2">
				</div>
			</div>
		</div>
		<center><a href = "shop/shop.jsp"><button class="btn btn-danger">返回</button></a></center>
	</body>


	<script>
    $(function () {
       $('#myTab a:first').tab('show');
          });
    function numberMinus(){
        var a=document.getElementById("goodNumber").value;
        if (a> 0) {
       		 a--;
     	     document.getElementById("goodNumber").value=a;
        };
    }
     function numberPlus(){
        var a=document.getElementById("goodNumber").value;
        if(a < <%= commodity.getStock()%>)
        	a++;
        document.getElementById("goodNumber").value=a;
    }
    function commentPoint(){
    	var value = document.getElementById("point").value;
    	alert(value);
    	document.getElementById("personal_point").value = value;
    }
    window.onload=alarm("<%=tips%>");
  </script>
</html>