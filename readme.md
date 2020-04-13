# 运行 war
run mvn: clean install 
http://localhost:8080/JavaWeb

# 标准目录结构
*	注意创建src/main/webapp/WEB-INF/web.xml
*	WEB-INF无法被外界访问， 可以放置受保护文件

# 字符编码
UTF-8: 3 个字节代表一个字符
GBK: 2个字节代表一个字符
详见URLEncoding->Encoding
一个字节 = 8 bit

# http status code
200:  2 打头 成功
400：  4 打头 客户端错误， 比如连接不上服务器
500：  5 打头 服务器错误
302： 重新定向， 服务器给出一个新的location, 要求客户端重新请求
304： last-modified, 如果服务器没有更新， 客户端使用上一次的缓存

# Servlet:
一个URL绑定一个Servlet
* 实现javax.servlet.Servlet接口
* 继承javax.servlet.GenericServlet抽象类
* 继承javax.servlet.http.HttpServlet抽象类， 但没有抽象方法 -- 最佳选择

servlet接口方法：
* void init(ServletConfig)
* void service(ServletRequest request, ServletResponse response)
* void destroy()
* String getServletInfo()
* ServletConfig getServletConfig()

	<!-- Servlet 配置 -->
    <servlet>
    	<servlet-name>ServletAName</servlet-name>
    	<!-- 类路径含pacakge name -->
    	<servlet-class>servlet.AServlet</servlet-class>
    	<!-- servletduixiang 启动参数 -->
    	<init-param>
    		<param-name>p1</param-name>
    		<param-value>v1</param-value>
    	</init-param>    	
    	<init-param>
    		<param-name>p2</param-name>
    		<param-value>v2</param-value>
    	</init-param>
    	<!-- Tomcat启动时init servlet -->
    	<load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
    	<servlet-name>ServletAName</servlet-name>
    	<url-pattern>/servlet_a</url-pattern>    	
    </servlet-mapping>      

## Servlet域对象:
*	ServletContext --单实例， 服务器启动时创建，服务器关闭时销毁
	*	内部有Map， 可以存取数据
	*	访问资源
	*	把资源读取为输入流 resource-> InputStream
	*	获取web.xml里的context-parameter
*	ServletRequest
	*	域对象：变量跨多个Servlet， 一个request由Servlet A 处理， 但Servlet A 需要Servlet B 帮助
	*	request.setAttribute("abc", "xxxx");
*	HttpSession

## Response: response响应流分字符流和字节流
*	PrintWriter out = response.getWriter(); //字符流
*	ServeltOutputStream out = response.getOutputStream(); //字节流
*	不能同时使用字符流和字节流
*	缓冲区： response.flushBuffer();  //将缓冲区数据立即response，而无需等待缓冲区满了才输出


## Eclipse 使用annotation, 而不是web.xml

## response
*	response.setContentType("text/html;charset=utf-8");
*	response.setHeader("Content-Type", ""text/html;charset=utf-8"");
*	response.setCharacterEncoding("utf-8");
*	response.setStatus(200);
*	response.sendError(404, "您要查找的资源不存在");
*	response.setHeader("Refresh", "5; URL=/JavaWeb/LoginServlet");  //自动跳转
*	response.setHeader("Location", "/JavaWeb/index.jsp");
*	response.sendRedirect("/JavaWeb/index.jsp"); //设置状态码302 + relocation

## request
*	request.getContextPath();
* 	request.getQueryString();  //获取parameter

	http://localhost:8080/hello/oneServlet?name=zhangSan
	getRequestURL()-> http://localhost:8080/hello/oneServlet?name=zhangSan
	getScheme()-> http
	getServerName()-> localhost
	getServerPort()-> 8080
	getContextPath()-> /hello
	getServletPath()-> /oneServlet
	getServletURI()-> /hello/oneServlet
	getQueryString()-> name=zhangSan

Post 请求编码问题：
request.setCharacterEncoding("utf-8");
String username = request.getParameter("username");
String password = request.getParameter("password");

Get 请求编码问题：
String username = request.getParameter("username");
String password = request.getParameter("password");
byte[] b1 = username.getBytes("iso-8859-1"); //回退
username = new String(b1, "utf-8"); //重解

## 请求转发 和 请求包含
请求转发：Servlet A 将请求转发给 Servlet B, A就不管了
请求包含：Servlet A 包含 Servlet B, A和B共同处理
RequestDispatcher rd = request.getRequestDispatcher("/BServlet"); // Servlet URL pattern
rd.forward(request, response);  //请求转发
rd.include(request, response);  //请求包含

## 路径
客户端路径：
	/项目名/资源 --> http://localhost:8080/项目名/资源
	使用： 超链接，表单，重定向，<img src="/项目名/资源">
	如果不用/：则是指当前目录： <a href="b.html" />  不建议使用
服务器端路径 
	/资源 --> http://localhost:8080/项目名/资源
	使用： 请求转发，请求包含，<url-pattern>, ServletContext于资源相关
Class路径： AClass.getResourceAsStream("/a.txt") //当前目录

## Session
一次会话的多次请求可以共享数据

## cookie
服务器把数据寄存在客户端： 都放在请求头或响应头
默认生命周期是浏览器，也可以保存在硬盘