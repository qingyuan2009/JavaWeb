package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseBuffTest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < 1024; i++) {
			response.getWriter().print("a");
		}		
		response.getWriter().flush();  // ����ˢ�»��������ѽ�������ͻ��ˣ� ��response������Ҫ����Ž���
		//response.flushBuffer();  // һ��
		try {
			Thread.sleep(5000);  //��5���response
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
