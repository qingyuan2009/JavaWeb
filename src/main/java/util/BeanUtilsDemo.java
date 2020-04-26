package util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsDemo {
    
    //BeanUtils������ʡ, ����ʡ�����㣬�Ƽ�ʹ��
    @Test
    public void func1() throws Exception {
        Class c = Person.class;
        Object o = c.newInstance();        
        BeanUtils.setProperty(o, "name", "Li");
        // age ���Զ�ת��Ϊint
        BeanUtils.setProperty(o, "age", "13");
        BeanUtils.setProperty(o, "sex", "male");
        System.out.println(o);        
        
        String name = BeanUtils.getProperty(o, "name");
        System.out.println(name);
    }
    
    //ʹ��map�Ѽ�ֵ�Է�װ��JavaBean
    @Test
    public void func2() throws Exception {
        Map<String, String> map = new HashMap();
        map.put("name", "WangWu");
        map.put("age", "17");
        map.put("sex", "female");  
        map.put("xxx", "xxxx");  //���Զ����ԣ�������
        Class c = Person.class;
        Object o = c.newInstance();
        BeanUtils.populate(o, map);
        System.out.println(o);
    }
    
    //����һ����װ
    @Test
    public void func3() {
        Map<String, String> map = new HashMap();
        map.put("name", "ZhaoLiu");
        map.put("age", "19");
        map.put("sex", "male");  
        Person p = CommonUtils.toBean(map, Person.class);
        System.out.println(p);        
    }
}
