<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String username = (String) session.getAttribute("name");
		if (username == null) {
			response.sendError(404);
			return;
		}
	%>

	<h1>主页 1</h1>
	用户:

	<%
		out.print("<p style='color:red'>" + username + "</p>");
	
		//URL 重写
		//如果cookie禁用， 会把sessionid 添加到url中, 
		String str = response.encodeURL("/JavaWeb/index2.jsp");
		out.print(str);
	%>

	<h3>欢迎</h3>
</body>
</html>