package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoodsServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String good = req.getParameter("name");
		// 获取cookie里所有good
		String allGoods = "";
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("goods")) {
					if (good.equals("Clear")) {
						cookie.setMaxAge(0); // 删除cookie
					} else {
						allGoods = cookie.getValue();
					}
				}
			}
		}
		//将cookie中的数据装入util class
		GoodUtil goodUtil = new GoodUtil(allGoods);
		if (!good.equals("Clear")) {
			goodUtil.addGood(good);
		}

		Cookie cookie = new Cookie("goods", goodUtil.getAllGood());
		System.out.println("cookie:" + goodUtil.getAllGood());

		resp.addCookie(cookie);
		resp.setContentType("text/html;charset=UTF-8");
		resp.sendRedirect(req.getContextPath() + "/goods.jsp");
	}

}
