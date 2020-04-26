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
        // ���BeanInfo
        BeanInfo info = Introspector.getBeanInfo(c);
        // �����������������
        PropertyDescriptor[] pds = info.getPropertyDescriptors();

        // ��ʡ Introspector, ���������´���
        /*
         * Method read = c.getMethod("getName"); //ͨ��get������ȡ��������
         * Class returnType = read.getReturnType();
         * Method write = c.getMethod("setName", returnType); //��ȡ��set����
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
