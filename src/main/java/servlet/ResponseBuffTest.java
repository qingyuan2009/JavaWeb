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
		response.getWriter().flush();  // 马上刷新缓冲区，把结果返还客户端， 但response还是需要五秒才结束
		//response.flushBuffer();  // 一样
		try {
			Thread.sleep(5000);  //过5秒才response
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
