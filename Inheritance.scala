package part2op

object Inheritance extends App {

  // signle abstarct method very similar to Interface in Go
  trait Action {
    def act(x:Int): Int
  }

  // with abstract classes as well
  abstract class Action:
    def act(x:Int): Int
  
  val aInstance:Action = new Action:
    override def act(x: Int): Int = x + 1
    
  val aFunkyInstace:Action = (x:Int) => x * x



  val i:Int = 18
  val s:String = "Jack"

  // Scala object companion - more OOP than in java, but pretty cool

  object Person {

    // Factory in one line - LOL
    def apply(name:String,age:Int):Person = new Person(name,age)

    private def printSomething():Unit = println("Something")
  }
  class Person(val name:String,val age:Int) {

    //  multi constructors; just like in java

    def this(name:String)={
      this(name,i)
    }

    def this() = {
      this(s, i)
    }
    // wtf - private and protected shared with object
    // with import Person._ imports all methods
    // so we can invke it without context, just printSomething
    final def pritnSomethingFromTheObject():Unit = Person.printSomething()
    
  }

  class Woman(override val name:String,override val age:Int) extends Person(name,age) {
    // just like - override val s:String = name
    super.pritnSomethingFromTheObject()
    // same as java
    // final, sealed - the same
    // can't override def pritnSomethingFromTheObject
  }


  val jack = Person("Jack",20) // under the hood Person.apply
}
