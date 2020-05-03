package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestParamTest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//由于Get方法不知道参数的编码，所以需要回退到字节， 然后重新编码
		username = new String(username.getBytes("iso-8859-1"), "utf-8" ); //回退 + 重编
		password = new String(password.getBytes("iso-8859-1"), "utf-8" ); //回退 + 重编
		response.setContentType("text/html;charset=UTF-8"); // set response encoding type
		response.getWriter().println(username + " " + password);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8"); // set response encoding type
		
		//Parameters
		String username = req.getParameter("username");
		String password = req.getParameter("password");		
		resp.getWriter().println(username + " " + password);
		
		//ParameterValue
		String[] hobby = req.getParameterValues("hobby");
		resp.getWriter().println(Arrays.toString(hobby));
		
		Map<String, String[]> map = req.getParameterMap();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String[] a = (String[]) entry.getValue();
			resp.getWriter().println("next : " + entry.getKey() + " - " + Arrays.toString(a));
		}
		
	
	}

}
