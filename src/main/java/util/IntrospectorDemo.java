package util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;

public class IntrospectorDemo {

    @Test
    public void func1() throws Exception {
        Class c = Person.class;
        Object o = c.newInstance();
        // 获得BeanInfo
        BeanInfo info = Introspector.getBeanInfo(c);
        // 获得属性描述符数组
        PropertyDescriptor[] pds = info.getPropertyDescriptors();

        // 内省 Introspector, 代替了以下代码
        /*
         * Method read = c.getMethod("getName"); //通过get方法获取参数类型
         * Class returnType = read.getReturnType();
         * Method write = c.getMethod("setName", returnType); //获取了set方法
         * c.invoke(write, "zhangsan");
         */
        for (PropertyDescriptor p : pds) {
            if (p.getName().equals("name")) {
                p.getWriteMethod().invoke(o, "zhangsan");
            }
        }
        System.out.println(o);
    }

}
