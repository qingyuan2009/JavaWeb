## 实际是通过reflection实现

## 需要有无参构造器
Class class = Class.forName("exe002.Person");
Object obj = class.newInstance();

## Bean property

property = setName(), 并不是 Person的成员变量 name

<bean id="Person"
	class="exe002.Person">
	<property name="name" value="Rui Zhou" />
</bean>

## create bean

 //通过Bean ID 获取Bean对象
 Person person = (Person) context.getBean("Person");  
        
 //也可以使用class,无需cast,但要求xml配置不能有两个相同的class
 Person person = context.getBean(Person.class);
        
 //如果xml配置有两个相同的class
 Person person = context.getBean("Person2", Person.class);