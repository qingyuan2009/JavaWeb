package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8"); //�Ƽ�
		//response.setHeader("Content-Type", "text/html;charset=UTF-8"); // Ҳ����
		// response.setCharacterEncoding("utf-8");  // Ҳ����, ���ͻ��˾Ͳ�֪����ô������
		response.getOutputStream().write("��Һ�".getBytes("UTF-8"));
		response.flushBuffer();  	//����������������response��������ȴ��������˲����			
	}

}
