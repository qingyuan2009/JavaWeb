package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossServletA extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/crossservlet_b");
		response.setContentType("text/html;charset=UTF-8"); //可以传header
		request.setAttribute("ABC", "xxx");
		rd.forward(request, response);  // 请求转发, 客户端不知道转发到B
	}

}
