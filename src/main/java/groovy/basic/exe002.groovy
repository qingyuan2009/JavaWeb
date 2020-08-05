package groovy.basic

// javabean, 无需setter方法
class JavaBeans{
    String a
}

def beans = new JavaBeans();
beans.a = "hello";
println(beans.a)

//GString ${var}
def a = "aaaaa";
def b = "bbbbb";
println("a=${a},b=${b}");  //GString

//闭包
def list = [1,2];
list.each { 
    println(it);
}
list.each { item->
    println(item);
}

//collect
def list2 = ["foo", "bar"];
def newList = [];
list2.collect(newList){
    it.toUpperCase()
}
println(newList);

//this, owner
def a1 = {
    println "a1 this " + this;
    println "a1 owner " + owner; //owner指向a1
    
    def b1 = {
        println "b1 this " + this;
        println "b1 owner " + owner; //owner指向b1
    }
    b1.call();    
}
a1.call();

//delegate
def scriptClosure={
    println "scriptClosure " + this;    
    println "scriptClosure " + owner; 
    println "scriptClosure " + delegate; 
}
println "before setDelegate";
scriptClosure.call();
scriptClosure.setDelegate("abc");
println "after setDelegate";
scriptClosure.call();




