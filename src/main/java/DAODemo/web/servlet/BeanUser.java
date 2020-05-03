package DAODemo.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAODemo.model.User;
import DAODemo.service.UserService;
import util.CommonUtils;

public class BeanUser extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8"); // set response encoding type
		
		//获取所有参数，封装到Map
		Map map = req.getParameterMap();
		User user = CommonUtils.toBean(map, User.class);	
		UserService userService = new UserService();
		try {
		    if (req.getParameter("add") != null) {		        
		        userService.AddUser(user);
		        resp.getWriter().println(user.toString()); 
		    }
		    
		    if (req.getParameter("login") != null) {
                User loginUser = userService.login(user.getUsername(), user.getPassword());
                resp.getWriter().println(loginUser.toString());               
            }
        }
        catch (Exception e) {           
            resp.getWriter().println(e);
        }
		
	}

}
