# ���� war
run mvn: clean install 
http://localhost:8080/JavaWeb

# ��׼Ŀ¼�ṹ
*	ע�ⴴ��src/main/webapp/WEB-INF/web.xml
*	WEB-INF�޷��������ʣ� ���Է����ܱ����ļ�

# �ַ�����
UTF-8: 3 ���ֽڴ���һ���ַ�
GBK: 2���ֽڴ���һ���ַ�
���URLEncoding->Encoding

# http status code
200:  2 ��ͷ �ɹ�
400��  4 ��ͷ �ͻ��˴��� �������Ӳ��Ϸ�����
500��  5 ��ͷ ����������
302�� ���¶��� ����������һ���µ�location, Ҫ��ͻ�����������
304�� last-modified, ���������û�и��£� �ͻ���ʹ����һ�εĻ���

# Servlet:
һ��URL��һ��Servlet
* ʵ��javax.servlet.Servlet�ӿ�
* �̳�javax.servlet.GenericServlet������
* �̳�javax.servlet.http.HttpServlet�����࣬ ��û�г��󷽷� -- ���ѡ��

servlet�ӿڷ�����
* void init(ServletConfig)
* void service(ServletRequest request, ServletResponse response)
* void destroy()
* String getServletInfo()
* ServletConfig getServletConfig()

	<!-- Servlet ���� -->
    <servlet>
    	<servlet-name>ServletAName</servlet-name>
    	<!-- ��·����pacakge name -->
    	<servlet-class>AServlet</servlet-class>
    	<!-- servletduixiang �������� -->
    	<init-param>
    		<param-name>p1</param-name>
    		<param-value>v1</param-value>
    	</init-param>    	
    	<init-param>
    		<param-name>p2</param-name>
    		<param-value>v2</param-value>
    	</init-param>
    	<!-- Tomcat����ʱinit servlet -->
    	<load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
    	<servlet-name>ServletAName</servlet-name>
    	<url-pattern>/servlet_a</url-pattern>    	
    </servlet-mapping>     

Servlet�����:
*ServletContext
*ServletRequest
*HttpSession



