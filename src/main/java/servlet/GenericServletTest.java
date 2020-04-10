package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GenericServletTest extends GenericServlet {

	@Override
	//service是GenericServlet的抽象方法，必须在子类实现
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String p3 = this.getInitParameter("p3");
		PrintWriter out = res.getWriter();		
		out.print(p3);
	}

}
