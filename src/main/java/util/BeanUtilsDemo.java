package util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsDemo {
    
    //BeanUtils依赖内省, 比内省更方便，推荐使用
    @Test
    public void func1() throws Exception {
        Class c = Person.class;
        Object o = c.newInstance();        
        BeanUtils.setProperty(o, "name", "Li");
        // age 会自动转换为int
        BeanUtils.setProperty(o, "age", "13");
        BeanUtils.setProperty(o, "sex", "male");
        System.out.println(o);        
        
        String name = BeanUtils.getProperty(o, "name");
        System.out.println(name);
    }
    
    //使用map把键值对封装到JavaBean
    @Test
    public void func2() throws Exception {
        Map<String, String> map = new HashMap();
        map.put("name", "WangWu");
        map.put("age", "17");
        map.put("sex", "female");  
        map.put("xxx", "xxxx");  //会自动忽略，不报错
        Class c = Person.class;
        Object o = c.newInstance();
        BeanUtils.populate(o, map);
        System.out.println(o);
    }
    
    //更进一步封装
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
