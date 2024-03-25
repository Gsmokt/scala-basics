package part4pm

object Closure extends App {
  
  val f: (Int, Int => Int)=>Int = (x:Int,y: Int => Int) => y(x)
  val hFunc:(Int) => Int = (x:Int) => x + 1
  println(f(3, hFunc))
  // or 
  println(f(3,_ + 1))
  // or
  println(f(3,1 + _))
  // or with new feather
  import scala.language.postfixOps
  println(f(3,1+))
  
  val func2 = (x:Int,y:Int) => x + y
  val func3 = func2.curried
  println(func3(3)(3))
  val func4 = Function.uncurried(func3)
  println(func4(3,3))
  
  val func: (Int => Int => Int) =  (x:Int) => (y:Int) => x + y
  println(func(4)(5))
  // also currying
  def func5(x:Int)(y:Int)(z:Int) = x + y + z
  // method to function
  val func6 = func5(3) _
  println(func6(3)(3))
  
  //By Name parameters; parameters called by block and lazy evaluate 
  def divideSafely(f: => Int):Option[Int] = {
    try {
      Some(f)
    } catch
      case e: ArithmeticException => None
  }
  
  val one = divideSafely {
    val x = 100
    val y = 5
    x / y
  }
  println(one)
}
