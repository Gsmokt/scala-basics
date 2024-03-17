package part3functions

import scala.util.Random

object Options extends App {

  // options instead of checking values
  // smoother way of dealing with potentially null functions

  val MyFirstOption: Option[Int] = Some(4)
  val noOption:Option[Int] = None

  def unsafeMethod():String = null

  val result = Option(unsafeMethod())
  println(result)

  def backupMethod():String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  def betterUnsafeMethod():Option[String] = None
  def betterBackupMethod():Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  val config:Map[String,String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host:String,port:String):Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None

  }

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(g => Connection.apply(h,g)))
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

}
