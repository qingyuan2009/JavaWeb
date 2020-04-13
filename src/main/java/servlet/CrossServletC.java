package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossServletC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/crossservlet_b");
		request.setAttribute("ABC", "xxx");
		rd.include(request, response);  // 请求转发, 客户端不知道转发到B
		response.getWriter().println("return back to Servlet C");	}
}
