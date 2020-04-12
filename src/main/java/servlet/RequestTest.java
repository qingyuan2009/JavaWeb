package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestTest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ABC", "xxx");
		//request×÷ÓÃÓò
		String str = (String) request.getAttribute("ABC");
		response.getWriter().println(str );
		
		String value = request.getHeader("User-Agent");
		response.getWriter().println(value);
		
		Enumeration e = request.getHeaderNames();
		while(e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value1 = response.getHeader(name);			
			response.getWriter().println(name + "=" + value1 );
		}
		
		String path = request.getContextPath();
		response.getWriter().println("Context path: " + path);
		
		String params = request.getQueryString();
		response.getWriter().println("Parameters:  " + params);
		
		String addr = request.getRemoteAddr();
		response.getWriter().println("Remote addr:  " + addr);
		
		
	}

}
