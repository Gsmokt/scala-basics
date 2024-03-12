package part2op

object ExceptionAndEnums extends App {
  val x:String = null

//  val aWeirdValue = throw new NullPointerException

  def getInt(withExceptions:Boolean):Int =
    if(withExceptions) throw new RuntimeException("No int for you")
    else 42

  class MyException extends Exception
  val exception = new MyException

  // Everything in scala is expression ♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥ :D
  val potentialFail = try {
    getInt(true)
  } catch {
//    case e:RuntimeException => println("caught a runtime exception")
//    case e:RuntimeException => throw exception
    case e:RuntimeException => "Something went wrong"
  } finally {
    println("finally")
  }

  println(potentialFail)


  enum Persmissions {
    case READ,WRITE,EXECUTE,NONE

    def openDocument():Unit =
      if(this == READ) println("opening document...")
      else println("reading not allowed")
  }

  enum PermissionsWithBits(bits:Int){
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }
  println(PermissionsWithBits.WRITE)
  val  somePermissions:Persmissions = Persmissions.READ

  somePermissions.openDocument()
}
