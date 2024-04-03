package lectures.part3concurrency

import lectures.part3concurrency.ThreadCommunication.SimpleContainer

object ThreadCommunication extends App {
  
  // holy shit, synchronized wait() and notify() work exactly the same
  // as data exchanging via no buffer goroutines, in Go it's it's automatic,
  // here we just need to put some endpoints
  
  class SimpleContainer:
    private var value: Int = 0

    def isEmpty: Boolean = value == 0
    def set(newValue: Int): Unit = value = newValue
    def get: Unit = {
      val result = value
      value = 0
      result
    }

  def naiveProdCons(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      while (container.isEmpty) {
        println("[consumer] actively waiting...")
      }
      println("[consumer] I have consumed " + container.get)
    })

    val producer = new Thread(() => {
      println("[producer] computing...")
      Thread.sleep(500)
      val value = 42
      println("[producer] I have produced, after long work, the value " + value)
    })

    consumer.start()
    producer.start()
  }

  naiveProdCons()  
  
  def smartProdCons(): Unit = {
    val container = new SimpleContainer
    
    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      container.synchronized {
        container.wait()
      }
      println("[consumer] I have consumed " + container.get)
    })
    
    val producer = new Thread(() => {
      println("[producer] Hard at work...")
      Thread.sleep(2000)
      val value = 42
      
      container.synchronized {
        println("[producer] I'm producing " + value)
        container.set(value)
        container.notify()
      }
    })
    
    consumer.start()
    producer.start()
  }
  
  smartProdCons()
}


