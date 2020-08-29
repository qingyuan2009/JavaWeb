package groovy.basic

//sort
def lst = [13,12,15,16, 0, -1];
def sortedLst = lst.sort();
println sortedLst;

def newLst = lst.sort{
    a,b -> a-b ? -1 : 1
}

//find all
def findAllLst = lst.findAll();
println  findAllLst;

def findAllLst2 = lst.findAll{ value -> value < 13};
println  findAllLst2;

//collect
def collectLst = lst.collect{ it * it}
println collectLst;

//inject
def list = ["love","you"];
def aa = list.inject("I") { sum, elem -> "$sum $elem" }
println aa;  //I love you

//eachWithIndex
def list2 = ["a", "b", "c"]
list2.eachWithIndex { String v, int index ->
    println v;
    println index
}






