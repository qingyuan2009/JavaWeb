<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	尝试正确和错误的用户名！	
	<form action="/JavaWeb/session_login" method="POST">
		username: <input type="text" name="username" value="zhou" /> <br> 
		password: <input type="password" name="password" /> <br> 
		<input type="submit" value="submit" />
	</form>	
	<%
		String msg = (String)request.getAttribute("msg");
		if (msg != null){
			out.print("<p style='color:red'>" + msg + "</p>");
		}
	%>
</body>
</html>