package Exercise1

//
// COMP40070 ABSTRACT FACTORY EXERCISE
// 

class Client:

  var product: Product = Product("ProductA")

  def foo() =
    product.doSomeWork()
    product = Product("ProductB")
    product.doSomeWork()
    product = Product("ProductC")
    product.doSomeWork()


abstract class Product{
  def doSomeWork(): Unit
}

object Product {
  private class ProductA extends Product{
    override def doSomeWork(): Unit = doYourStuff()
    def doYourStuff() =
      println("I'm a ProductA, doing my stuff")
  }

  private class ProductB extends Product {
    override def doSomeWork(): Unit = doIt()

    def doIt() =
      println("I'm a ProductB, doing it")
  }

  private class ProductC extends Product {
    override def doSomeWork(): Unit = perform
    def perform =
      println("I'm a ProductC, performing")
  }

  def apply(productName: String):Product = {
    productName match {
      case "ProductA" => new ProductA()
      case "ProductB" => new ProductB()
      case "ProductC" => new ProductC()
    }
  }
}


@main def main(): Unit =
  val myClient = Client()
  myClient.foo()
  