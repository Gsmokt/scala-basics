package lectures.part3concurrency

import java.awt.PrintJob

object JWMConcurrencyProblems {
  def runInParallel(): Unit =
    var x = 0
    val thread1 = new Thread(() => {
      x = 1
    })
   
    val thread2 = new Thread(() => {
      x = 2
    })
    
    thread1.start()
    thread2.start()
    println(x)
  
  class BankAccount(var amount: Int)
  
  def buy(account: BankAccount,thing:String,price:Int):Unit =
    account.amount -= price

  def buySafe(account: BankAccount,thing:String,price:Int):Unit =

    // !!!!!!!!!!!!!!!!
    // synchronized works the same as mutex.Lock and mutex.Unlock in Go
    // but here in parallel, but in this example result with threads
    // is the same as channels and goroutines in Go
    account.synchronized {
      account.amount -= price
    }

  def inceptionThreads(maxThreads: Int, i: Int = 1): Thread =
    new Thread(() => {
      if (i < maxThreads) {
        val newThread = inceptionThreads(maxThreads, i + 1)
        // works the same as in Go, new thread (like goroutine) 
        // start, join to block this piece of code like: Lock, Unlock, wg.Done() 
        // goroutine finishes job, close channel if buffer or read/write data by channel
        // to prevent deadlock
        newThread.start()
        newThread.join()
      }
      println(s"Hello from thread $i")
    })
  
  def minMax(): Unit =
    var x = 0
    val threads = (1 to 100).map(_ => new Thread(() => x += 1))
    threads.foreach(_.start())
    
  def demoSleepFallacy(): Unit  =
    var message = ""
    val awesomeThread = new Thread(() => {
      Thread.sleep(1000)
      message = "Scala is awesome"
    })
    
    message = "Scala sucks"
    awesomeThread.start()
    Thread.sleep(1001)
    println(message)
    
  def demoBankingProblem(): Unit =
    (1 to 10000).foreach { _ => 
      val account = BankAccount(50000)
      val tThread1 = new Thread(() => buy(account,"shoes",3000))
      val tThread2 = new Thread(() => buy(account,"iPhone",4000))
      tThread1.start()
      tThread2.start()
      tThread1.join()
      tThread2.join()
      if(account.amount != 430000) println(s"Aha i've just broken the bank: ${account.amount}")
      
    }
    
  def main(array: Array[String]): Unit = {
    runInParallel()
    inceptionThreads(50).start()
  }
}
