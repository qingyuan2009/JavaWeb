package groovy.basic

import groovy.transform.TypeChecked

//switch
def x = 20;
switch(x) {
    case [1,2,3,4,5]:
        println "list object";
        break;
    case "foo":
        println "object";
        break;
    case 10..100:
        println "scope object"
        break;
    case Date:
        println "date object"
        break;
}

//meta class ���巽��
String.metaClass.uppers = {-> toUpperCase()};
println "aaa".uppers()

Integer.metaClass.say = { -> "I am integer"};
def i = new Integer(100);
println i.say();

//ǿ���ͼ��ע�� @TypeChecked
class Foos{
    int i= 42.0;    
}
@TypeChecked
class Foo{
    //int i= 42.0;  //�������
}

//ǿ����ָ��
def list = ["a","b","c"];
def newList = [];
list.collect(newList){
    String item -> item.toUpperCase();   
}
println newList

//��Ԫ�����
def a = null;
def b = a?:"bbb";  //����bʱ��a�Ƿ���ֵ�������ֵ��b=a, �������bbb
println b;

//��ȫ����
def person;
String name = person?.getName();
println name;



