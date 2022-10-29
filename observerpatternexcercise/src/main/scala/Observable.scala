import java.util
import scala.collection.mutable.ListBuffer

trait Observable {

  val observers = ListBuffer[Observer]()

  //takes anonymous function as argument, and then use apply method to
  //actual effect to take place
  def attach(addObserver: () => Unit): Unit = addObserver.apply()

  //use of short-hand syntax to notify each observers
  def notifyObservers(subject : Participant): Unit =
    observers.foreach(_.notification(subject))

  def detach(deleteObserver: () => Unit): Unit =
    deleteObserver.apply()
    println("No one is observing this game")

}
