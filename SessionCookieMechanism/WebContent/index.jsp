<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<!-- 内嵌css样式 -->
<style>
/*清除默认样式*/
* {
	padding: 0px;
	margin: 0px;
}
</style>
</head>
<body>
	<h1>注册模块</h1>
	<div>
		<form action="EnrollServlet" method="POST">
			<input type="text" name="phone" required placeholder="phone">
		<br><input type="text" name="userName" required placeholder="用户名">	
		<br><input type="password" name="userPwd" required placeholder="密码">
		<br><input type="submit" value="submit">	
		</form>
	</div>
	<h1>登录模块</h1>
	<form action="LoginServlet" method="Post">
		<input type="text" name="phone">
	<br><input type="password" name="userPwd">
	<br><input type="submit" value="submit">
	</form>

</body>
</html>