<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Servlet Post
	<br>
	<form action="/JavaWeb/servlet_b" method="POST">
		username: <input type="text" name="username" /> <br> 
		password: <input type="password" name="password" /> <br> 
		<input type="submit" value="submit" />
	</form>
	Hello Servlet
	<a href="/JavaWeb/servlet_a">Servlet</a>
	<br> Hello GenericServlet
	<a href="/JavaWeb/servlet_c">GenericServlet</a>
	<br> Hello HttpServlet
	<a href="/JavaWeb/servlet_d">HttpServlet</a>
	<br>
	doPost
	<br>
	<form action="/JavaWeb/servlet_d" method="POST">
		username: <input type="text" name="username" /> <br> 
		password: <input type="password" name="password" /> <br> 
		<input type="submit" value="submit" />
	</form>
	<br>	
	<a href="/JavaWeb/LoginServlet">LoginServlet-Eclipse generated</a>
</body>
</html>