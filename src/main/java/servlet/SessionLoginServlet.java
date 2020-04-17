package servlet;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */

public class SessionLoginServlet extends HttpServlet {       
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8"); //可以传header
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		if (username.equalsIgnoreCase("zhou")){			
			session.setAttribute("name", username);
			resp.sendRedirect(req.getContextPath() + "/index1.jsp");			
		} else {
			session.removeAttribute("name");
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			req.setAttribute("msg", "用户名错误");
			rd.forward(req, resp);
		}
	}

}
