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
	<h1>主页 2</h1>
	用户:

	<%
		out.print("<p style='color:red'>" + username + "</p>");
	%>

	<h3>欢迎</h3>
</body>
</html>