import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//这里使用Servlet接口
public class AServlet implements Servlet {
	
	private ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet 出生");
		this.config = config;
	}

	@Override
	public ServletConfig getServletConfig() {		
		return config;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("Servlet 处理请求");
		//获取servlet启动参数
		String p1 = this.config.getInitParameter("p1");
		String p2 = this.config.getInitParameter("p2");
		System.out.println(p1 + " " + p2);
		//获取servlet启动参数方法2
		Enumeration e = this.config.getInitParameterNames();
		while(e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = config.getInitParameter(name);
			System.out.println(name + "=" + value);
		}
	}

	//没什么用
	@Override
	public String getServletInfo() {		
		return null;
	}

	//当Tomcat容器关闭时调用
	@Override
	public void destroy() {
		System.out.println("Servlet destroy");
	}

}
