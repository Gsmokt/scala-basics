package lectures.part2

object Monads extends App {

  trait Attempt[+A]{
    def flatMap[B](f: A => Attempt[B]): Attempt[B]
  }

  object Attempt {
    def apply[A](a: => A):Attempt[A] =
      try {
        Success(a)
      } catch
        case e: Throwable => Fail(e)
  }
  
  case class Success[+A](value:A) extends Attempt[A]{
    def flatMap[B](f: A => Attempt[B]): Attempt[B] =
      try {
        f(value)
      } catch
        case e: Throwable => Fail(e)
  }

  case class Fail(e: Throwable) extends Attempt[Nothing]{
    def flatMap[B](f: Nothing=> Attempt[B]): Attempt[B] = this
  }
  
  class Lazy[+A](value: => A){
    def use: A = value
    def flatMap[B](f: (=> A ) => Lazy[B]): Lazy[B] = f(value)
  }
  
  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy[A](value)
  }
  
  val LazyInstance = Lazy {
    println("Smth")
    42
  }
  println(LazyInstance)
  
  val flatMappedInstance = LazyInstance.flatMap(x => Lazy{
    10 * x
  })

  val flatMappedInstance2 = LazyInstance.flatMap(x => Lazy {
    10 * x
  })
  println(flatMappedInstance.use)
  println(  flatMappedInstance2.use)
}
