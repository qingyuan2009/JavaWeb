package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class CookieTestA extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//cookie
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replace("-", "").toUpperCase();
		Cookie cookie = new Cookie("id", id); //��ֵ��
		cookie.setMaxAge(60 * 60); //Ӳ�̱���1Сʱ
		resp.addCookie(cookie);
		resp.setContentType("text/html;charset=UTF-8"); 
		resp.getWriter().println("������������cookie");
	}

}