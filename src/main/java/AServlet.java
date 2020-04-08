import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//����ʹ��Servlet�ӿ�
public class AServlet implements Servlet {
	
	private ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet ����");
		this.config = config;
	}

	@Override
	public ServletConfig getServletConfig() {		
		return config;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("Servlet ��������");
		//��ȡservlet��������
		String p1 = this.config.getInitParameter("p1");
		String p2 = this.config.getInitParameter("p2");
		System.out.println(p1 + " " + p2);
		//��ȡservlet������������2
		Enumeration e = this.config.getInitParameterNames();
		while(e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = config.getInitParameter(name);
			System.out.println(name + "=" + value);
		}
	}

	//ûʲô��
	@Override
	public String getServletInfo() {		
		return null;
	}

	//��Tomcat�����ر�ʱ����
	@Override
	public void destroy() {
		System.out.println("Servlet destroy");
	}

}
