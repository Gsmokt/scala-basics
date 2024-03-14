package part3functions

object BrainDead extends App {

  // let's go, deep dive !!
  def counter(f:Int => Int,n:Int,x:Int):Int =
    if(n <= 0) x
    else counter(f,n -1, f(x))

  // ok that's simple, like a javascript style
  // but stacking function all the time
  val function: (Int) => Int = (x:Int) => x*x
  println(counter(function,10,2))

  
  // 💀 💀 💀 💀 💀 💀 💀 💀 💀 💀 
  val plusOne = (x:Int) => x + 1
  
  def nTimesBetter(f:Int=>Int,n:Int):(Int => Int) =
    if(n <= 0) (x: Int) => x
    else (x:Int) => nTimesBetter(f,n-1)(f(x))
    // ok some mindfuck at first look
    // but wait a minute
    // first ret (x:int) but next loops doesn't
    // cause of ()() immediately stacking x as plusOne 
    // so it makes ()(f(f(f(f(f(x)))
    // something like this but as a result
    // reminds compose as reduce callback in js 


    // ok so !!!!!!!!!!!!
    // so it looks like nTimesBetter(plusOne,1)(plusOne(1))
    // and immediately it's like (2) => nTimesBetter(plusOne,2)(plusOne(2))
    // it's tricky (x:Int) is ret, but stacking the same time next loops 
    // so it can be ret in the end
    // heh, nice


    val plus10 = nTimesBetter(plusOne,10)


  
}
