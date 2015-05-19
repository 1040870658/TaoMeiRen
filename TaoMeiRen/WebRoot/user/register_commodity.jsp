<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>
  添加商品
  </h1>
  <br>
  <form action="/TaoMeiRen/RegisterCommodity" method="post">
 <h2>商品名称：<input type="text" name="name" value="${commodity.name }"/><br/></h2>
 <h2>商品价格：<input type="text" name="price" value="${commodity.price }"/><br/></h2>
 <h2>商品数量：<input type="text" name="stock" value="${commodity.stock }"/><br/></h2>
 <h2>出售方式：</h2><input type="radio" name="dealtype" value="一口价"checked="checked"/>一口价
 			 <input type="radio" name="dealtype" value="团购"/>团购
 			 <input type="radio" name="dealtype" value="竞拍"/>竞拍<br/>
 <h2>商品描述：</h2><textarea  name="description" cols="80" rows="10">${commodity.description }</textarea><br/></br>
  <input type="submit" value="登记"/>
</form>
  </body>
</html>
