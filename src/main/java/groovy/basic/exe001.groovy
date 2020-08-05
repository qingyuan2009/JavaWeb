package groovy.basic

//��ӡ
println "hello groovy"

//����
def v = 0;
v = new Date();
println(v);

//List
def list = [1,2]
list.leftShift(3)  //��������λ
list.push(0);
list.add("abc");
list << "����";
list += "��һ�ּ���";
println(list);
//[0, 1, 2, 3, abc, ����, ��һ�ּ���]

//for each
list.forEach({println(it)});  //it ��Ĭ�ϲ���
list.forEach{ it->
    println(it)
};

//sort
def lst = [13,12,15,16];
def newlst = lst.sort();
println newlst;

def newlst2 = lst.sort{
    a,b -> a-b ? -1 : 1
}

//Map
def map = [a:2, b: new Date()];
map.put("aaaaa", "bbbbb");
println(map);
map.a = "a value";
println(map.b);


//For loop
100.times{ println it };  // 0..99
100.times{ println "hi" }; 



