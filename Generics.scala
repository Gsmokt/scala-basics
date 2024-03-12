package part2op

object Generics extends App {

  // luckily almost the same as in Go

//  class Thing
//  class Item extends Thing

//  class Person[A](age:A){} // just 0-1 types
//  class Woman[A](age:A) extends Person[A](age:A)

//  class Person[+A](age: A) {
    // not so simple
//    def push[Y >: A](item: Y): SomeList[Y] = new SomeList[Y]
  // if only def push(item:A)
  // compiler doesn't know what type to make a list
//  }

//  val p: Person[Thing] = new Person[Item](new Item)
//   as +A can go every sub classes

//  class Person[-A](age: A) {}
//  val p: Person[Item] = new Person[Thing](new Thing)
  // as -A can go higher class


//  class Person[A <: Thing](age:A){}
//  val p: Person[Thing] = new Person(new Item)
// as <: or >: sub or super class


}
