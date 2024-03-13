package part3functions

// LOL - functions are objects cause of JVM xdxdxd
trait SomeFunc[A,B]{
  def apply(e:A):B
}

object WhatsAFunction extends App {

  // so we can make this to invoke object as function :DDDDDDD
  val func = new SomeFunc[String,String]{
    override def apply(e: String): String = e + " World"
  }
  println(func("Hello"))

  // but we can take a shortcut
  val concatStrings:(String,String) => String = new Function2[String,String,String] {
    override def apply(one:String,two:String):String = one + two
  }

  // but at least to make function a function
  // there is a type function (A,B) => R
  // so we can write function as function xddddddddddddddddddddddddddddd
  // LOL
  // xd
  



  println(concatStrings("hello","Wordl"))

}



class Person[T](name:T){
  def apply(n:T):T = n
}


