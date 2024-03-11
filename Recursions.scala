package basics

object Funcations extends  App {
  def aFunction(a: String,b:Int): String =
    a + " " + b

  println(aFunction("hello",3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())

  def aRepeatedFunction(aString:String,n:Int):String = {
    if(n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("hello",3))

  def aFunctionWithSideEffects():Unit = (1)

  def aGreetingFunction(s: String):String = {
    s
  }
  println(aGreetingFunction("My greatest greetings!"))

  def factorial(n:Int):Int =
    if(n <= 0) 1
    else n * factorial(n - 1)

  println(factorial(3))

  def fibonacci(i:Int):Int =
    if(i <= 1) 1
    else  fibonacci(i - 1) + fibonacci(i - 2)


  println(fibonacci(3))

  def concatenateFunction(x:Int):Int =
    1 + concatenateFunction(x + 1)

  def loopFunc(x:Int,y: => Int):Int = {
    x
  }
  println(loopFunc(2,concatenateFunction(4)))


  // Simple counter

    val newCounter = new Counter
  println(newCounter.inc(1).print)


  class Counter(i:Int = 0) {
    def retInc = {
      new Counter(i + 1)
    }

    def retDec = {
      new Counter(i - 1)
    }

    def inc(i:Int):Counter = {
      if(i >= 10) this
      else retInc.inc(i +  1)
    }

    def dec(i:Int):Counter = {
      if(i <= 0) this
      else retInc.dec(i - 1)
    }

    def print = println(i)
  }
}

