package part2op

object Inheritance extends App {



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
