package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseBuffTest2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < 1024 * 16 - 1; i++) {
			response.getWriter().print("a");
		}		
		
		System.out.println(response.isCommitted());
		response.getWriter().print("a");
		// »º³åµ½16K,×Ô¶¯response
		System.out.println(response.isCommitted());		
	}

}
