
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<link rel="stylesheet" href="./css/loginForm.css">

</head>

<body>
	<form class="box" action="./loginServlet.do" method="POST">
		<h2>login</h2>
		<input type="text" name="user" placeholder="UserName">
		<input type="password" name="pw"  placeholder="password">
		<input type="submit" value="Login">
	</form>
</body>
</html>