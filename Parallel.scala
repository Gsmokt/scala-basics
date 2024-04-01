package lectures.part3concurrency

import java.util.concurrent.Executors

object Parallel extends App {
  
  // clear parallel
  
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Running in parallel")
  })

  aThread.start()

  val threadHello = new Thread(() => (1 to 5).foreach(_ => println("hello")))
  val threadGoodbye = new Thread(() => (1 to 5).foreach(_ => println("goodbye")))

  threadHello.start()
  threadGoodbye.start()

  val pool = Executors.newFixedThreadPool(10)
  pool.execute(() => println("something in the thread pool"))

  pool.execute(() => {
    Thread.sleep(1000)
    println("done after 1 sec")
  })

  pool.execute(() => {
    Thread.sleep(1000)
    println("almost done")
    Thread.sleep(1000)
    println("done after 2 sec")
  })

  // it shut the thread and all executors run after shut,  but existing execution 
  pool.isShutdown // we can check the shut status in this way
  // is still running
  pool.shutdown()
  pool.execute(() => println("should not appear"))
  
  // but that close everything immediately, and running execution will 
  // throw exception
  pool.shutdownNow()
}
