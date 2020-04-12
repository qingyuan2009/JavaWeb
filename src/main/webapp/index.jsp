<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	实现Servlet接口的Post
	<br>
	<form action="/JavaWeb/servlet_b" method="POST">
		username: <input type="text" name="username" /> <br> 
		password: <input type="password" name="password" /> <br> 
		<input type="submit" value="submit" />
	</form>
	<a href="/JavaWeb/servlet_a">实现Servlet接口</a><br>	
	<a href="/JavaWeb/servlet_c">继承抽象类GenericServlet</a><br>	
	<a href="/JavaWeb/servlet_d">继承HttpServlet</a>	
	<a href="/JavaWeb/servlet_f">测试ServletContext全局属性</a><br> 
	继承HttpServlet的Post
	<br>
	<form action="/JavaWeb/servlet_d" method="POST">
		username: <input type="text" name="username" /> <br> 
		password: <input type="password" name="password" /> <br> 
		<input type="submit" value="submit" />
	</form>
	<br>	
	<a href="/JavaWeb/LoginServlet">Response编码问题</a><br> 
	<a href="/JavaWeb/servlet_g">缓冲区机制</a>
	<a href="/JavaWeb/servlet_h">16K缓冲区机制</a><br> 
</body>
</html>