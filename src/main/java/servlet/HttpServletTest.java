package servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class HttpServletTest extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("doGet");	
		//ServletContext
		ServletContext sc = this.getServletContext();
		String path = sc.getRealPath("WEB-INF/web.xml");
		resp.getWriter().println(path);	
		//InputStream
		InputStream input = sc.getResourceAsStream("WEB-INF/web.xml");
		resp.getWriter().println(IOUtils.toString(input));
		//Context域属性是同服务器同寿命, 在其他的Servlet中也能访问到
		sc.setAttribute("ABC", "XXX");
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("doPost");
	}

}
