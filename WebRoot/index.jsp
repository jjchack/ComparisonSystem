<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style1.css">
  </head>
  
  <body>
<div class="container">
	<section id="content">
		<form action="${pageContext.request.contextPath}/servlet/userLog" method="post">
			<h1>登录</h1>
			<div>
				<input type="text" placeholder="Username" required="" id="username" name="username" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="password" name="password" />
			</div>
			<div>
				<input type="submit" value="登录" />
				<span><font color="red">${requestScope.msg}</font></span>
				<a href="register.jsp">暂无账户，注册新账户</a>
			</div>
		</form><!-- form -->
		<div class="button">
			<a href="http://cst.hpu.edu.cn/">河南理工大学计算机学院</a>
		</div><!-- button -->
	</section><!-- content -->
</div><!-- container -->
</body>
</html>
