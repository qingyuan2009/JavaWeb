## ʵ����ͨ��reflectionʵ��

## ��Ҫ���޲ι�����
Class class = Class.forName("exe002.Person");
Object obj = class.newInstance();

## Bean property

property = setName(), ������ Person�ĳ�Ա���� name

<bean id="Person"
	class="exe002.Person">
	<property name="name" value="Rui Zhou" />
</bean>

## create bean

 //ͨ��Bean ID ��ȡBean����
 Person person = (Person) context.getBean("Person");  
        
 //Ҳ����ʹ��class,����cast,��Ҫ��xml���ò�����������ͬ��class
 Person person = context.getBean(Person.class);
        
 //���xml������������ͬ��class
 Person person = context.getBean("Person2", Person.class);