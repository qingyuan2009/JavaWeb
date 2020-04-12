package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

public class RequestBean extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8"); // set response encoding type
		
		//获取所有参数，封装到Map
		Map map = req.getParameterMap();
		Student student = new Student();
		try {
			//使用Bean反射把map反射到student对象中
			BeanUtils.populate(student, map);
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}
		resp.getWriter().println(student.toString());
	}

}
