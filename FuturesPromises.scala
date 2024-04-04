package lectures.part3concurrency

import javax.swing.plaf.FontUIResource
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import scala.util.{Failure, Random, Success, Try}

object FuturesPromises extends App {

  def calculateMeaningOfLife: Int = {
    Thread.sleep(2000)
    42
  }

  val aFuture = Future {
    calculateMeaningOfLife
  }

  println(aFuture.value)  // Option[Try[Int]]

  println("Waiting on the future...")
  aFuture.onComplete(t => t match
    case Success(meaningOfLife) => println(s"Meaning of the life is $meaningOfLife")
    case Failure(exception) => println(s"$exception")
  )

  Thread.sleep(3000)

  case class Profile(id:String,name:String) {
    def poke(anotherProfile:Profile):Unit =
      println(s"${this.name} poking ${anotherProfile.name}")
  }

  object SocialNetwork {
    val names  = Map(
      "fb.id.1-zuck" -> "Mark",
      "fb.id.2-bill" -> "Bill"
    )

    val friends = Map(
      "fb.id.1-zuck" -> "fb.id.2-bill"
    )

    val random = new Random()

    // it's like promise
    def fetchProfile(id:String):Future[Profile] = Future {
      Thread.sleep(random.nextInt(300))
      Profile(id,names(id))
    }

    def fetchBestFriend(profile: Profile):Future[Profile] = Future {
      Thread.sleep(random.nextInt(400))
      val bfId = friends(profile.id)
      Profile(bfId,names(bfId))
    }

    val mark = SocialNetwork.fetchProfile("fb.id.1-zuck")
    mark.onComplete {
      case Success(markProfile) => {
        val bill = SocialNetwork.fetchBestFriend(markProfile)
        bill.onComplete {
          case Success(billProfile) => markProfile.poke(billProfile)
          case Failure(exception) => println(s"${exception.getMessage}")
        }
      }
      case Failure(exception) => println(s"${exception.getMessage}")
    }
  Thread.sleep(1000)
  val nameOnTheWall = mark.map(profile => profile.name)

  val marksBestFriend = mark.flatMap(profile => SocialNetwork.fetchBestFriend(profile))

  val zucksBestFriendRestricted = marksBestFriend.filter(profile => profile.name.startsWith("z"))

  for {
    mark <- SocialNetwork.fetchProfile("fb.id.1-zuck")
    bill <- SocialNetwork.fetchBestFriend(mark)
  } mark.poke(bill)

  Thread.sleep(1000)
  }

  // do some staff in case of exception
  val aProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown id").recover {
    case e: Throwable => Profile("fb.id.0-dummy","Forever alone")
  }

  // do some staff in case of exception, but - butt - buttt- buttttt
  // we know for sure the second try will not throw an exception
  val aFetchedProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown id").recover {
    case e: Throwable => SocialNetwork.fetchProfile("fb.id.0-dummy")
  }

  // if first call will go to hell, the fallback will take a chance,
  // if it also will f... up, then fallbackResult will take first exception
  // clear? clear
  // no? f... off
  val fallbackResult = SocialNetwork.fetchProfile("unknown id").fallbackTo(SocialNetwork.fetchProfile("fb.id.0-dummy"))

  case class User(name:String)
  case class Transaction(sender:String,receiver:String,amount:Double,status:String)

  object BankingApp {
    val name = "App"

    def fetchUser(name:String): Future[User] = Future {
      Thread.sleep(500)
      User(name)
    }

    def createTransaction(user:User,merchantName:String,amount:Double):Future[Transaction] = Future {

      Thread.sleep(1000)
      Transaction(user.name,merchantName,amount,"Success")
    }
    def purchase(username:String,item:String,metchantName:String,cost:Double):String = {
      val transactionStatusFuture = for {
        user <- fetchUser(username)
        transaction <- createTransaction(user,metchantName,cost)
      } yield transaction.status

      Await.result(transactionStatusFuture,2.second)
    }
  }

  println(BankingApp.purchase("Daniel","iPhone 12", "wtf", 2600))


  val promise = Promise[Int]()
  val future = promise.future

  future.onComplete {
    case Success(r) => println("[consumer] I've received " + r)
  }

  val producer = new Thread(() => {
    println("[producer] crunching numbers...")
    Thread.sleep(1000)

    promise.success(42)
    println("[producer] done")
  })

  producer.start()
  Thread.sleep(1000)
  
  // ok, it's dummy example, but that's why Go concurrency is OP
  // in every kind of situation ( for instance retry the request ) we can 
  // put some for select loop and check for result from channel in some unit of time
  // or revceive timeout from ctx and stop looping
}
