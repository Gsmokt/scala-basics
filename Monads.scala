package lectures.part2

object Monads extends App {

  // that scary monad is just wrapper of value or type etc. 
  // allows to do some magic tricks like transform value or change type 
  // there are complicated implementations
  // but for god sake really????????? 
  // that's just it ??????????
  // f*** me or f*** that 
  // tell me more or less :/

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

    case class SafeValue[+T](private val internalValue: T) {
    def get: T = synchronized {
      internalValue
    }

    def transform[S](transformer: T => SafeValue[S]) : SafeValue[S] = synchronized {

      transformer(internalValue)
    }
  }

  def gimmeSafeValue[T](value: T): SafeValue[T] = SafeValue(value)

  val safeString: SafeValue[String] = gimmeSafeValue("Smth")
  val string = safeString.get
  val upperString = string.toUpperCase()
  val upperSageString = SafeValue(upperString)
  val upperSafeString2 = safeString.transform(s => SafeValue(s.toUpperCase()))
}
