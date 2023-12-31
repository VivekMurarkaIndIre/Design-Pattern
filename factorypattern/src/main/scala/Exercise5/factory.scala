package Exercise5

//
// COMP40070 ABSTRACT FACTORY EXERCISE
//

class Client(productFactory: ProductFactory):

  var product:Product = productFactory.createProductA()

  def foo() =
    product.doSomeWork()
    product = productFactory.createProductB()
    product.doSomeWork()
    product = productFactory.createProductC()
    product.doSomeWork()
    product = productFactory.createProductD()
    product.doSomeWork()

trait Product {
  def doSomeWork(): Unit
}

trait ProductFactory{
  def createProductA(): ProductA

  def createProductB(): ProductB

  def createProductC(): ProductC

  def createProductD(): ProductD
}

object CoolProductFactory extends ProductFactory {
  override def createProductA(): ProductA = ProductA("CoolProductA")
  override def createProductB(): ProductB = ProductB("CoolProductB")
  override def createProductC(): ProductC = ProductC("CoolProductC")
  override def createProductD(): ProductD = ProductD("CoolProductD")
}

object NormalProductFactory extends ProductFactory {
  override def createProductA(): ProductA = ProductA("NormalProductA")
  override def createProductB(): ProductB = ProductB("NormalProductB")
  override def createProductC(): ProductC = ProductC("NormalProductC")
  override def createProductD(): ProductD = ProductD("NormalProductD")
}

object DeadlyProductFactory extends ProductFactory {
  override def createProductA(): ProductA = ProductA("DeadlyProductA")
  override def createProductB(): ProductB = ProductB("DeadlyProductB")
  override def createProductC(): ProductC = ProductC("DeadlyProductC")
  override def createProductD(): ProductD = ProductD("DeadlyProductD")
}

abstract class ProductA extends Product{
  def doSomeWork(): Unit
}

abstract class ProductB extends Product{
  def doSomeWork(): Unit
}

abstract class ProductC extends Product{
  def doSomeWork(): Unit
}

abstract class ProductD extends Product{
  def doSomeWork(): Unit
}

object ProductA {
  private class CoolProductA extends ProductA{
    override def doSomeWork(): Unit = doYourStuff()
    def doYourStuff(): Unit =
      println("I'm a ProductA, doing my Cool stuff")
  }

  private class NormalProductA extends ProductA {
    override def doSomeWork(): Unit = doYourStuff()

    def doYourStuff(): Unit =
      println("I'm a ProductA, doing my Normal stuff")
  }

  private class DeadlyProductA extends ProductA {
    override def doSomeWork(): Unit = doYourStuff()

    def doYourStuff(): Unit =
      println("I'm a ProductA, doing my Deadly stuff")
  }

  def apply(productName: String):ProductA = {
    productName match {
      case "CoolProductA" => new CoolProductA()
      case "NormalProductA" => new NormalProductA()
      case "DeadlyProductA" => new DeadlyProductA()
    }
  }
}

object ProductB {
  private class CoolProductB extends ProductB {
    override def doSomeWork(): Unit = doIt()

    def doIt(): Unit =
      println("I'm a ProductB, doing it cool")
  }

  private class NormalProductB extends ProductB {
    override def doSomeWork(): Unit = doIt()

    def doIt(): Unit =
      println("I'm a ProductB, doing it normally")
  }

  private class DeadlyProductB extends ProductB {
    override def doSomeWork(): Unit = doIt()

    def doIt(): Unit =
      println("I'm a ProductB, doing it deadly")
  }
  def apply(productName: String):ProductB = {
    productName match {
      case "CoolProductB" => new CoolProductB()
      case "NormalProductB" => new NormalProductB()
      case "DeadlyProductB" => new DeadlyProductB()
    }
  }
}

object ProductC {

  private class CoolProductC extends ProductC {
    override def doSomeWork(): Unit = perform
    def perform: Unit =
      println("I'm a ProductC, performing cool stuff")
  }

  private class NormalProductC extends ProductC {
    override def doSomeWork(): Unit = perform
    def perform: Unit =
      println("I'm a ProductC, performing normal stuff")
  }

  private class DeadlyProductC extends ProductC {
    override def doSomeWork(): Unit = perform

    def perform: Unit =
      println("I'm a ProductC, performing deadly stuff")
  }

  def apply(productName: String):ProductC = {
    productName match {
      case "CoolProductC" => new CoolProductC()
      case "NormalProductC" => new NormalProductC()
      case "DeadlyProductC" => new DeadlyProductC()
    }
  }
}

object ProductD {

  private class CoolProductD extends ProductD {
    override def doSomeWork(): Unit = work
    def work: Unit =
      println("I'm a ProductD, performing cool stuff")
  }

  private class NormalProductD extends ProductD {
    override def doSomeWork(): Unit = work
    def work: Unit =
      println("I'm a ProductD, performing normally")
  }

  private class DeadlyProductD extends ProductD {
    override def doSomeWork(): Unit = work

    def work: Unit =
      println("I'm a ProductD, performing deadly stuff")
  }

  def apply(productName: String):ProductD = {
    productName match {
      case "CoolProductD" => new CoolProductD()
      case "NormalProductD" => new NormalProductD()
      case "DeadlyProductD" => new DeadlyProductD()
    }
  }
}

@main def main(): Unit =
  println("Enter 1 for Cool, 2 for normal, 3 for deadly ")
  val input = scala.io.StdIn.readInt()
  var productFactory: ProductFactory= null
  if(input == 1){
    println("Creating Cool Products")
    productFactory= CoolProductFactory
  } else if (input == 2) {
    println("Creating Normal Products")
    productFactory = NormalProductFactory
  } else if (input == 3) {
    println("Creating Deadly Products")
    productFactory = DeadlyProductFactory
  } else{
    println("Invalid Input")
  }
  val myClient = Client(productFactory)
  myClient.foo()
