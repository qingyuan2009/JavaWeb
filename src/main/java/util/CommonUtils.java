package util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class CommonUtils {
    
    // <T> 可以使调用者不需要cast
    public static <T> T toBean(Map map, Class<T> clazz) {       
        
        T bean;
        try {
            bean = clazz.newInstance();
            BeanUtils.populate(bean, map);
            return bean;                    
        }
        catch (Exception e) { 
            //强转runtime exception
            throw new RuntimeException(e);
        }  
    }
}
