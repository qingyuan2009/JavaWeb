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
    	<servlet-class>AServlet</servlet-class>
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

Servlet域对象:
*ServletContext
*ServletRequest
*HttpSession



