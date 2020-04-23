package util;

import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;

import org.junit.Test;

import servlet.Student;

public class ReflectionDemo {

	// ��ȡ��ʵ��
	// 1. ������ Class c1 = Object.class;
	// 2. ���� Class c2 = "Hello".getClass();
	// 3. ��ȫ���� Class c3 = Class.forName("uitl.ReflectionDemo");

	@Test
	public void func1() throws ClassNotFoundException {
		Class c1 = int.class;
		Class c2 = int[].class; // ������
		Class c3 = "Hello".getClass();
		Class c4 = Class.forName("util.ReflectionDemo");
		Class c5 = Comparable.class; // �ӿ���

		System.out.println(c3.getName());
		System.out.println(c3.getSimpleName());
		System.out.println(c4.getName());
		System.out.println(c4.getSimpleName());

		System.out.println(c1.getSuperclass()); // null; int û�и���
		System.out.println(c3.getSuperclass());
		System.out.println(c5.isInterface());
	}

	// �̳���
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
		Student stu = c1.newInstance(); // ����Ĭ�Ϲ�����
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

}