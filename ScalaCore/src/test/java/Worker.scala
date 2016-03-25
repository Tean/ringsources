import akka.actor.Actor

/**
  * Created by tanshengyong on 16/3/16.
  */
class Worker extends Actor {

  def calculatePiFor(start: Int, nrOfElements: Int): Double = {
    var acc = 0.0
    for (i ← start until (start + nrOfElements))
      acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)

    //    Thread.sleep(10 * Random.nextInt(1000))
    println(Thread.currentThread() + ":" + self + " from " + start + " to " + (start + nrOfElements) + ":" + acc)

    (1 to 1000).foldLeft(Array(0, 1))((a, b) => a ++ Array(a(a.length - 2) + a(a.length - 1)))

    val st = System.currentTimeMillis()
    (1 to 1000).foreach(
      _ => println(_)
    )
    val ed = System.currentTimeMillis()
    println(ed-st)

    acc
  }

  def receive = {
    case Work(start, nrOfElements) ⇒
      sender ! Result(calculatePiFor(start, nrOfElements)) // perform the work
  }
}
