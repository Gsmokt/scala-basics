package part4pm

object PatternsEverywhere extends App {

  // scala 3 brings new syntax with no curly braces
  // almost like python, so GJ !

  try {

  }catch
    case e:RuntimeException => "runtime"
    case npe:NullPointerException => "npe"
    case _=> "something else"


  val list = List(1,23,4,5)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  val tuples = List((1,2),(3,4))
  val filterTuples = for {
    (first,second) <- tuples
  } yield first * second
  println(filterTuples)

  val tuple = (1,2,3)
  val (a,b,c) = tuple

  val head :: tail = list

  val mappedList = list.map {
    case v if v % 2 ==0 => v +" is even"
    case 1 => "the one"
    case _ => "something else"
  }

  class Animal:
    def eat():Unit =
      println("I'm eating")
    end eat

    def grow():Unit =
      println("I'm growing")
    end grow

  end Animal

  val aSpecialAnimal = new Animal:
    override def eat():Unit = println("I'm special method")

}
