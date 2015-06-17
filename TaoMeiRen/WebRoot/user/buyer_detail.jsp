<%@page import="com.tao.model.Commodity"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%

Commodity commodity = (Commodity)request.getAttribute("detail_commodity");
String[] type = {"null","一口价","团购","竞拍"};
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
String serverPath = session.getServletContext().getRealPath("/").replace("\\", "/");
String image =commodity.getImageUrl().get(0);
session.setAttribute("commodityOfOrder",commodity);
String fail = (String)request.getAttribute("fail");
if(fail == null)
	fail = "";
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
			
		<!--项目内容-->
		<div class="container">
			<div class="row">
				<div class="col-md-2">
				</div>
				<div class="col-md-3" style="padding-top: 70px; padding-left: 40px">
					<img class="img-circle" src="<%=image %>"
						style="width: 220px; height: 220px">
					<div style="padding-top: 10px; padding-left: 85px;">

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

					</div>
					<div class="col-md-3">
					</div>
				</div>
			</div>
		</div>


		<!--用户评论-->
		
		<center><form id = "back_form"action = "BuyerServlet"><button onclick = "submit_back()"class="btn btn-danger">返回</button></form></center>
	</body>


	<script>
    $(function () {
       $('#myTab a:first').tab('show');
          });
    function numberMinus(){
        var a=document.getElementById("goodNumber").value;
        if (a> 1) {
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
    function submit_back(){
    	var form = document.getElementById("back_form");
    	form.submit();
    }
    window.onload=alarm("<%=fail%>");
  </script>
</html>