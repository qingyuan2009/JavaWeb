package util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class CommonUtils {
    
    // <T> ����ʹ�����߲���Ҫcast
    public static <T> T toBean(Map map, Class<T> clazz) {       
        
        T bean;
        try {
            bean = clazz.newInstance();
            BeanUtils.populate(bean, map);
            return bean;                    
        }
        catch (Exception e) { 
            //ǿתruntime exception
            throw new RuntimeException(e);
        }  
    }
}
