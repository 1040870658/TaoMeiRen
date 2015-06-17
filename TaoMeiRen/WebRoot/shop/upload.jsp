<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<meta http-equiv="pragma" content="no-cache" />
		<span style="color: #ff0000;"><base target="_self"></span>
		<title>商品图片上传</title>
	</head>
	<body>
		<h5>图片上传</h5><hr/>
		<p style="color: red">${requestScope.errorMsg}</p>
		<form id="form1" name="image" action="<%=path%>/UploadImageServlet?type=bookUpload" method="post" enctype="multipart/form-data">
			<div>注:图片大小最大不能超过3M!</div>
			<div><input type="file" name="file_upload"/></div>
			<div><input type="submit" value="上传"/></div>
		</form>
	</body>
</html>