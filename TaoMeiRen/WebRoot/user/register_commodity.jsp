<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	if (request.getSession().getAttribute("sessionUser") == null) {
		response.sendRedirect(request.getContextPath()
				+ "/user/login.jsp");
		return;
	}
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String serverPath = session.getServletContext().getRealPath("/")
			.replace("\\", "/");
	String filePath = (String) request.getAttribute("path");
	if (filePath == null)
		filePath = "null";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>Register Your Commodity</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" media="screen" href="user/css/btncss.css" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body style="background-color: #FFFFCC">
		<form action="/TaoMeiRen/RegisterCommodity" method="post">
			<div align=center>
				<h1>
					添加商品
				</h1>
				<div style="float: left; width: 30%; margin-left: 15%;">
					<h3>
						商品图片：
					</h3>
					<div>
						<input type="hidden" id="photo_id" name="photo"
							value="images/noimg.png">
						<br />
						<img id="img_id" alt="" src="<%=filePath%>" width="256px"
							height="256px">
						<br />
						<div>
							<input type="file" name="file_upload">
						</div>
						<div>
							<input type="submit" value="上传图片"
								onclick="var f=this.form; f.action='UploadImageServlet'; f.method='POST'; f.enctype='multipart/form-data'; f.submit();" />
						</div>
					</div>
					<h2>
						商品描述：
					</h2>
					<textarea name="description" cols="35" rows="10">${commodity.description }</textarea>
					<br /></br>
				</div>
				<div style="float: right; width: 30%; margin-right: 15%">
					<h3>
						出售方式：
					</h3>
					<input type="radio" name="dealtype" value="一口价" checked="checked" onclick="showFix('active_num','deadline','active_price')" checked="checked" />
					一口价
					<input type="radio" name="dealtype" value="团购" onclick="showCollection('active_num','deadline','active_price')"/>
					团购
					<input type="radio" name="dealtype" value="竞拍" onclick="showAuction('deadline','active_price','active_num')"/>
					竞拍
					<br />
					<br>
					<h2>
						商品名称：
						<input type="text" name="name" value="${commodity.name }" />
						<br />
					</h2>
					<br />
					<h2>
						商品价格：
						<input type="text" name="price" value="${commodity.price }" />
						<br />
					</h2>
					<br />
					<h2 >
						商品数量：
						<input id = "stock_text" type="text" name="stock" value="${commodity.stock }" />
						<br />
					</h2>
					<br />
					<h2 id="deadline">
						截止日期：
						<input type="text" name="deadline">
					</h2>
					<br />
					<h2 id="active_num">
						最低人数：
						<input type="text" name="active_num">
					</h2>
					<br />
					<h2 id="active_price">
						最低价格：
						<input type="text" name="active_price">
					</h2>
					<br />

				</div>
				<div style="clear: both"></div>
				<input type="hidden" name="image_src" value="<%=filePath%>" />
				<input id="btn" type="submit" value="登记" />
				<input id="btn" type="submit" value="取消"
					onclick="var f=this.form; f.action='SellerServlet'; f.method='POST';; f.submit();" />
			</div>
		</form>
	<script>
	window.onload=hide2('active_num','deadline','active_price');
	function showhide(id, ids, idss) {
		showh2(id, ids);
		hide(idss);
	}
	function showFix(id, ids, idss){
		hide2(id,ids,idss);
		document.getElementById("stock_text").style.display="";	
	}
	function showCollection(id, ids, idss){
		showhide(id,ids,idss);
		document.getElementById("stock_text").style.display="";	
	}
	function showAuction(id,ids,idss){
		showhide(id,ids,idss);
		var cObj = document.getElementById("stock_text");
      	cObj.setAttribute("readOnly",'true');
      	cObj.style.color="grey";
		cObj.value = 1;
	}
	function hide2(id, ids, idss) {
		document.getElementById(id).style.display = "none";
		document.getElementById(ids).style.display = "none";
		document.getElementById(idss).style.display = "none";
	}
	function showh2(id, ids) {
		document.getElementById(id).style.display = "";
		document.getElementById(ids).style.display = "";
		//alert("I am an alert box!!")style="display: none;"
	}
	function hide(id) {
		document.getElementById(id).style.display = "none";
	}
	</script>

	</body>
</html>
