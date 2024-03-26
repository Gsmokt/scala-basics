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
  
  // so we can verify for nullability in a simple way
  println(List(Some(1),Some(2),None,None).flatMap(x => x))


  // tips for better readability
  val forLoops = for {
    x <- chars
    a <- numbers
  } yield x.toString + a

  // it also means nested for loops for humans 
  val forComprehension = for {x <- List(1,2,3,4);y <- List('a','b','c','d')} yield x + y.toString
  println(forComprehension)

  // and also
  val newMap = chars.flatMap { x =>
    numbers.map { a =>
      x.toString() + a
    }
  }

  // reduce works the same as everywhere but fold starts with acumulator, reduce with first item
  println((1 to 10).foldLeft(0)(_ + _))
  println((1 to 10).reduceLeft(_ + _))
  // but it's useless in such cases, cause the are simple methods like .sum, .produce, .min, .max etc. 
}
