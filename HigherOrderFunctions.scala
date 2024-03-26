package part3functions

object HigherOrderFunctions extends App {

  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val sub = chars.flatMap(x => numbers.map(a => x.toString() + a))
  println(sub)

  // ok very interesting thing, we can use HOFs with Option[] types 
  // so we can play around nullability
  // but in fact it changes the purpose of use
  println(Some(5).asInstanceOf[Option[Int]].flatMap(x => Some(x + 1)))
  println(None.asInstanceOf[Option[Int]].flatMap(x => Some(x + 1)))


  // tips for better readability
  val forLoops = for {
    x <- chars
    a <- numbers
  } yield x.toString + a

  // and also
  val newMap = chars.flatMap { x =>
    numbers.map { a =>
      x.toString() + a
    }
  }
}
