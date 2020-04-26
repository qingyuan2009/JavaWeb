package util;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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

    // 使用Class创建实例
    @Test
    public void func3() throws Exception {
        Class<Student> c1 = Student.class;
        Student stu = c1.newInstance(); // 调用默认构造器
    }

    // 从property文件中读取class
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

    // 使用Constructor创建实例
    @Test
    public void func5() throws Exception {
        Class<Student> c1 = Student.class;
        // 获取public 构造器
        Constructor[] constructors = c1.getConstructors();
        // 获取所有构造器
        Constructor[] constructors2 = c1.getDeclaredConstructors();
        // 获取没有参数的public构造器
        Constructor con1 = c1.getConstructor();
        // 获取带参数的public构造器
        Constructor con2 = c1.getConstructor(int.class, String.class, int.class);

        // 使用Constructor创建实例
        Constructor<Student> con = c1.getConstructor(int.class, String.class, int.class);
        Student stu = con.newInstance(3, "ABC", 13);
        System.out.println(stu.toString());
    }

    // 获取String class里所有构造器及参数
    @Test
    public void func6() throws Exception {
        Class<String> c1 = String.class;
        // 获取所有构造器
        Constructor[] constructors2 = c1.getDeclaredConstructors();
        for (Constructor con : constructors2) {
            System.out.print(Modifier.toString(con.getModifiers()) + " ");
            System.out.print(con.getDeclaringClass().getSimpleName() + "(");
            Class[] paramType = con.getParameterTypes();
            for (int i = 0; i < paramType.length; i++) {
                if (i == paramType.length - 1) {
                    System.out.print(paramType[i].getSimpleName());
                } else {
                    System.out.print(paramType[i].getSimpleName() + ",");
                }
            }
            System.out.println(")");
        }
    }

    //invoke
    @Test
    public void func7() throws Exception {
        Class<Student> c1 = Student.class;
        Method[] method = c1.getMethods();
        Method[] method2 = c1.getDeclaredMethods();
        Method m1 = c1.getMethod("getName");
        Method m2 = c1.getMethod("setName", String.class);
        
        //invoke
        Object stu = c1.newInstance(); // 调用默认构造器创建实例
        //如果调用static方法， 可以使用 invoke(null, ...)
        m2.invoke(stu, "zhou");
        System.out.println(stu.toString());
    }
    
    @Test
    public void func8() throws Exception {
        Class<Student> c1 = Student.class;
        Object stu = c1.newInstance();
        //获取本类和父类的public成员变量
        Field[] fields = c1.getFields();
        //获取本类所有的成员变量, 但不包括父类
        Field[] fields2 = c1.getDeclaredFields();
        
        //获取本类和父类的指定名称的public成员变量
        //Field field = c1.getField("name");
        
        ////获取本类指定名称的成员变量
        Field field2 = c1.getDeclaredField("name");      
        
        //对于private 成员变量需要设置访问许可
        field2.setAccessible(true);
        field2.set(stu, new String("Wang"));
        Object value = field2.get(stu);
        System.out.println(value);    
    }

}
