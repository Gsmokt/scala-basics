package part3functions

object HigherOrderFunctions extends App {

  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val sub = chars.flatMap(x => numbers.map(a => x.toString() + a))
  println(sub)


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
