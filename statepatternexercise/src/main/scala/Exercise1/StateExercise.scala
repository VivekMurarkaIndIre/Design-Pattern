package Exercise1



abstract class PersonState(){
  def vote(age: Int): Unit
  def applyForBuspass(age: Int): Unit
  def conscript(age: Int): Unit
}


class Child() extends PersonState(){

  override def vote(age: Int): Unit = println(s"$age: too young to vote")
  override def applyForBuspass(age: Int): Unit = println(s"$age: too young for a bus pass")
  override def conscript(age: Int): Unit = println(s"$age: too young to be conscripted")
}

class Adult() extends PersonState(){

  override def vote(age: Int): Unit = println(s"$age: vote accepted")
  override def applyForBuspass(age: Int): Unit = println(s"$age: too young for a bus pass")
  override def conscript(age: Int): Unit = println("Here is your gun")
}

class Pensioner() extends PersonState(){

  override def vote(age: Int): Unit = println(s"$age: vote accepted")
  override def applyForBuspass(age: Int): Unit = println(s"$age: pass granted")
  override def conscript(age: Int): Unit = println(s"$age: too old to be conscripted")
}

class Person:
  var age = 0
  var state: PersonState = new Child()

  def incrementAge() =
    age += 1
    if age == 18 then
      state = new Adult()
    else if age == 65 then
      state = new Pensioner()



  def canPerform(): Unit=
    state.vote(age)
    state.applyForBuspass(age)
    state.conscript(age)


@main def hello: Unit =
  val p = Person()
  for i <- 1 to 80 do
    p.incrementAge()
    p.canPerform()

