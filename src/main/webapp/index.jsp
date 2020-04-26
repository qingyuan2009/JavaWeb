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
	<a href="/JavaWeb/servlet_i?username=abc&password=123">Request作用域</a><br>
	<a href="/JavaWeb/servlet_j?username=张三&password=李四">Parameters Get的编码问题</a><br>
	Parameters Post的编码问题
	<form action="/JavaWeb/servlet_j" method="POST">
		username: <input type="text" name="username" value="张三"/> <br> 
		password: <input type="password" name="password" value="李四"/> <br> 
		hobbies: <input type="checkbox" name="hobby" value="Internet"/ > 上网
		<input type="checkbox" name="hobby" value="Swimming"/ > 游泳
		<input type="checkbox" name="hobby" value="Soccer"/ > 足球
		<input type="submit" value="submit" />
	</form>
	<br>
	使用Bean来封装Post 
	<form action="/JavaWeb/servlet_k" method="POST">
		id: <input type="text" name="number" value="1"/> <br> 
		name: <input type="text" name="name" value="李四"/> <br> 
		age: <input type="text" name="age" value="13"/><br> 
		<input type="submit" value="submit" />
	</form>	
	<br>	
	<a href="/JavaWeb/crossservlet_a">跨Servlet--请求转发</a><br> 
	<a href="/JavaWeb/crossservlet_c">跨Servlet--请求包含</a><br>
	<a href="/JavaWeb/cookietest_a">服务器设置cookie</a><br>
	<a href="/JavaWeb/cookietest_b">客户端返还cookie</a><br>
	<a href="/JavaWeb/goods.jsp">cookie 浏览商品</a><br>
	<a href="/JavaWeb/session_servletA">Session中设置变量</a><br>
	<a href="/JavaWeb/session_servletB">Session中获取变量</a><br>
	<a href="/JavaWeb/login.jsp">Session login 例子</a><br>
	<a href="/JavaWeb/index2.jsp">Session login 第二页</a><br>
	使用Bean来封装Post高级用法使用CommonUtils
	<form action="/JavaWeb/bean_person" method="POST">
		name: <input type="text" name="name" value="1"/> <br> 
		age: <input type="text" name="age" value="13"/> <br> 
		sex: <input type="text" name="sex" value="男"/><br> 
		<input type="submit" value="submit" />
	</form>		
</body>
</html>