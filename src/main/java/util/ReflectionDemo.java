package util;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import javax.swing.JFrame;

import org.junit.Test;

import servlet.Student;

public class ReflectionDemo {

	// 获取类实例
	// 1. 类名： Class c1 = Object.class;
	// 2. 对象： Class c2 = "Hello".getClass();
	// 3. 类全名： Class c3 = Class.forName("uitl.ReflectionDemo");

	@Test
	public void func1() throws ClassNotFoundException {
		Class c1 = int.class;
		Class c2 = int[].class; // 数组类
		Class c3 = "Hello".getClass();
		Class c4 = Class.forName("util.ReflectionDemo");
		Class c5 = Comparable.class; // 接口类

		System.out.println(c3.getName());
		System.out.println(c3.getSimpleName());
		System.out.println(c4.getName());
		System.out.println(c4.getSimpleName());

		System.out.println(c1.getSuperclass()); // null; int 没有父类
		System.out.println(c3.getSuperclass());
		System.out.println(c5.isInterface());
	}

	// 继承链
	@Test
	public void func2() {
		Class c1 = JFrame.class;
		Class supperClass = c1.getSuperclass();
		while (supperClass != null) {
			System.out.println(supperClass.getName());
			supperClass = supperClass.getSuperclass();
		}
	}

	@Test
	public void func3() throws Exception {
		Class<Student> c1 = Student.class;
		Student stu = c1.newInstance(); // 调用默认构造器
	}

	@Test
	public void func4() throws Exception {
		Properties prop = new Properties();
		InputStream input = getClass().getResourceAsStream("props.properties");
		prop.load(input);	
		String classPath = prop.getProperty("ClassName");
		if (classPath != null) {
			Class c1 = Class.forName(classPath);
			c1.newInstance();
			System.out.println(c1.getName());
		}
		// Java 8 , print key and values
		prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
	}
	
	@Test
	public void func5() throws Exception {
	   Class<Student> c1 = Student.class;
	   //获取public 构造器
	   Constructor[] constructors = c1.getConstructors();
	   //获取所有构造器
	   Constructor[] constructors2 = c1.getDeclaredConstructors();
	   
	   //获取没有参数的public构造器
	   Constructor con1 = c1.getConstructor();
	   //获取带参数的public构造器
	   Constructor con2 = c1.getConstructor(int.class, String.class, int.class);
	}
	
	@Test
    public void func6() throws Exception {
       Class<String> c1 = String.class;
       
       //获取所有构造器
       Constructor[] constructors2 = c1.getDeclaredConstructors();
       
       for (Constructor con: constructors2) {
           System.out.print(con.getDeclaringClass().getSimpleName() + "(");
           Class[] paramType = con.getParameterTypes();
           for (int i = 0; i <paramType.length; i++) {
               if (i == paramType.length-1) {
                   System.out.print(paramType[i].getSimpleName());
               }else {
                   System.out.print(paramType[i].getSimpleName() + ","); 
               }
           }           
           
           System.out.println( ")");
       }
       
      
    }

}
