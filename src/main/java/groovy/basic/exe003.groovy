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

//meta class 定义方法
String.metaClass.uppers = {-> toUpperCase()};
println "aaa".uppers()

Integer.metaClass.say = { -> "I am integer"};
def i = new Integer(100);
println i.say();

//强类型检查注解 @TypeChecked
class Foos{
    int i= 42.0;    
}
@TypeChecked
class Foo{
    //int i= 42.0;  //编译错误
}

//强类型指定
def list = ["a","b","c"];
def newList = [];
list.collect(newList){
    String item -> item.toUpperCase();   
}
println newList

//三元运算符
def a = null;
def b = a?:"bbb";  //定义b时看a是否有值，如果有值则b=a, 否则就是bbb
println b;

//安全访问
def person;
String name = person?.getName();
println name;



