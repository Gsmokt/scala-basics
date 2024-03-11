package part2op

object InfixNotations extends App {


  val Two = {
    2
  }

  val Three = {
    3
  }

  class one(i: Int = 1){
    def +(second:Int):one = new one(i + second)

    def ==(n:Int):Boolean = i == n
  }

  // overloading operators xd

  val One = new one
  println(One + Two == Three) // xd

  println(1.+(2)) // operators are also methods LOL
                  // same as above

}
