<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello JSP
	<br>
	<form action="/JavaWeb/index.jsp" method="POST">
		username: <input type="text" name="username" /> <br> 
		password: <input type="password" name="password" /> <br> 
		<input type="submit" value="submit" />
	</form>
	Hello Servlet
	<a href="/JavaWeb/servlet_a" >Access Servlet</a>
</body>
</html>