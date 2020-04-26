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
һ���ֽ� = 8 bit

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
    	<servlet-class>servlet.AServlet</servlet-class>
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

## Servlet�����:
*	ServletContext --��ʵ���� ����������ʱ�������������ر�ʱ����
	*	�ڲ���Map�� ���Դ�ȡ����
	*	������Դ
	*	����Դ��ȡΪ������ resource-> InputStream
	*	��ȡweb.xml���context-parameter
*	ServletRequest
	*	����󣺱�������Servlet�� һ��request��Servlet A ���� ��Servlet A ��ҪServlet B ����
	*	request.setAttribute("abc", "xxxx");
*	HttpSession
	* 	��������رվ�����

## Response: response��Ӧ�����ַ������ֽ���
*	PrintWriter out = response.getWriter(); //�ַ���
*	ServeltOutputStream out = response.getOutputStream(); //�ֽ���
*	����ͬʱʹ���ַ������ֽ���
*	�������� response.flushBuffer();  //����������������response��������ȴ����������˲����


## Eclipse ʹ��annotation, ������web.xml

## response
*	response.setContentType("text/html;charset=utf-8");
*	response.setHeader("Content-Type", ""text/html;charset=utf-8"");
*	response.setCharacterEncoding("utf-8");
*	response.setStatus(200);
*	response.sendError(404, "��Ҫ���ҵ���Դ������");
*	response.setHeader("Refresh", "5; URL=/JavaWeb/LoginServlet");  //�Զ���ת
*	response.setHeader("Location", "/JavaWeb/index.jsp");
*	response.sendRedirect("/JavaWeb/index.jsp"); //����״̬��302 + relocation

## request
*	request.getContextPath();
* 	request.getQueryString();  //��ȡparameter

	http://localhost:8080/hello/oneServlet?name=zhangSan
	getRequestURL()-> http://localhost:8080/hello/oneServlet?name=zhangSan
	getScheme()-> http
	getServerName()-> localhost
	getServerPort()-> 8080
	getContextPath()-> /hello
	getServletPath()-> /oneServlet
	getServletURI()-> /hello/oneServlet
	getQueryString()-> name=zhangSan

Post ����������⣺
request.setCharacterEncoding("utf-8");
String username = request.getParameter("username");
String password = request.getParameter("password");

Get ����������⣺
String username = request.getParameter("username");
String password = request.getParameter("password");
byte[] b1 = username.getBytes("iso-8859-1"); //����
username = new String(b1, "utf-8"); //�ؽ�

## ����ת�� �� �������
����ת����Servlet A ������ת���� Servlet B, A�Ͳ�����
���������Servlet A ���� Servlet B, A��B��ͬ����
RequestDispatcher rd = request.getRequestDispatcher("/BServlet"); // Servlet URL pattern
rd.forward(request, response);  //����ת��
rd.include(request, response);  //�������

## ·��
�ͻ���·����
	/��Ŀ��/��Դ --> http://localhost:8080/��Ŀ��/��Դ
	ʹ�ã� �����ӣ������ض���<img src="/��Ŀ��/��Դ">
	�������/������ָ��ǰĿ¼�� <a href="b.html" />  ������ʹ��
��������·�� 
	/��Դ --> http://localhost:8080/��Ŀ��/��Դ
	ʹ�ã� ����ת�������������<url-pattern>, ServletContext����Դ���
Class·���� AClass.getResourceAsStream("/a.txt") //��ǰĿ¼

## Session
һ�λỰ�Ķ��������Թ�������

## cookie
�����������ݼĴ��ڿͻ��ˣ� ����������ͷ����Ӧͷ
Ĭ�������������������Ҳ���Ա�����Ӳ��
cookie.setMaxAge(-1): ֻ��������У� ��������رգ�cookieʧЧ
cookie.setMaxAge(60*60): cookie��Ӳ�̱���һСʱ
cookie.setMaxAge(0): ɾ��cookie

## session
�������˶��� ���ǿͻ��˶���
sessionԭ�����cookie, cookie��ֻ�����һ��session ID, session�����ڷ������ˣ�
cookie: jsessionid = uuid
���ͻ�������ʱ���ᷢ��session ID, ����������ƥ��Ѱ�ҡ� cookie������������������رա�
������������ֳ�ʱ�����session����������٣� �����ͻ��˼�ʹ��session IDҲ�Ҳ���session���ݡ�
�������session��ʱ

## URL ��д��
���cookie����������ʹ��URL��д�� ��URL׷��;jsessionid=session.getId();
ʹÿ��URL-> http://localhost:8080/project/url;jsessionid=uuid
���jsessionid�ɷ�������ӣ� ���� response.encodeURL("/project/index.jsp")

## ����
Ӳ���ϵ�Class A {} --> ������Ϊ A.class  --> ���ڴ��� JVM �� Class: Class(Field, Method, Constructor)

## JavaBean
JavaBean���ԣ� ��getter/setter�����ĳ�Ա����
����Ҫ���޲ι�����
�������������2���ַ���Ҫôȫ��д��ҪôȫСд��id or ID --> setId(), setID()

��֪�� class һ���� ����һ��
      Student.class,  age
ͨ�����䴴��һ��Student���󣬲�Ϊage��ֵ13

���裺
1. Object o = c.newInstance();
2. age������ĸ��Ϊ��д��get/set����-> getAge,setAge
Method read = c.getMethod("getAge"); //ͨ��get������ȡ��������
Class returnType = read.getReturnType(); 
Method write = c.getMethod("setAge", returnType); //��ȡ��set����
c.invoke(write, 13);

���ϲ����������ʡʵ�֣�Introspector
1. ͨ��class��ȡBeanInfo
2. ͨ��BeanInfo��ȡ����������������������飺 PropertyDescriptor
3. ͨ��PropertyDescriptor����ȡget��set����

��ѷ�ʽ��BeanUtils


      



