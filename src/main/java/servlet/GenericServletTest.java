package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GenericServletTest extends GenericServlet {

	@Override
	//service��GenericServlet�ĳ��󷽷�������������ʵ��
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String p3 = this.getInitParameter("p3");
		PrintWriter out = res.getWriter();		
		out.print(p3);
	}

}
