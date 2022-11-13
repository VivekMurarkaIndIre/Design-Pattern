import Exercise1.Product
//
// COMP40070 ABSTRACT FACTORY EXERCISE
//

class Client(productFactory: ProductFactory):

  var product:Product = productFactory.createProductA()
  //val productA = ProductA()

  def foo() =
    //productA.doYourStuff()
    product.doSomeWork()

    //val myProductB = ProductB()
    //myProductB.doIt()

    product = productFactory.createProductB()
    product.doSomeWork()
    //val productC = ProductC()
    //productC.perform
    product = productFactory.createProductC()
    product.doSomeWork()

trait Product {
  def doSomeWork(): Unit
}

trait ProductFactory{
  def createProductA(): ProductA

  def createProductB(): ProductB

  def createProductC(): ProductC
}

class CoolProductFactory extends ProductFactory {
  override def createProductA(): ProductA = ProductA("CoolProductA")
  override def createProductB(): ProductB = ProductB("CoolProductB")
  override def createProductC(): ProductC = ProductC("CoolProductC")
}

class NormalProductFactory extends ProductFactory {
  override def createProductA(): ProductA = ProductA("NormalProductA")
  override def createProductB(): ProductB = ProductB("NormalProductB")
  override def createProductC(): ProductC = ProductC("NormalProductC")
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

  def apply(productName: String):ProductA = {
    productName match {
      case "CoolProductA" => new CoolProductA()
      case "NormalProductA" => new NormalProductA()
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

  def apply(productName: String):ProductB = {
    productName match {
      case "CoolProductB" => new CoolProductB()
      case "NormalProductB" => new NormalProductB()
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

  def apply(productName: String):ProductC = {
    productName match {
      case "CoolProductC" => new CoolProductC()
      case "NormalProductC" => new NormalProductC()
    }
  }
}

@main def main(): Unit =
  println("Enter 1 for Cool and 2 for uncool")
  val input = scala.io.StdIn.readInt()
  var productFactory: ProductFactory= null
  if(input == 1){
    println("Creating Cool Products")
    productFactory= new CoolProductFactory()
  }else if(input == 2){
    println("Creating Normal Products")
    productFactory = new NormalProductFactory()
  }else{
    println("Invalid Input")
  }
  val myClient = Client(productFactory)
  myClient.foo()
