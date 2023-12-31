package Exercise3

abstract class PersonState(){
  def vote(age: Int): Unit
  def applyForBuspass(age: Int): Unit
  def conscript(age: Int): Unit
  def applyForMedicalCard(age: Int): Unit
}


class Child() extends PersonState(){

  override def vote(age: Int): Unit = println(s"$age: too young to vote")
  override def applyForBuspass(age: Int): Unit = println(s"$age: too young for a bus pass")
  override def conscript(age: Int): Unit = println(s"$age: too young to be conscripted")
  override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card granted")
}

class Adult() extends PersonState(){

  override def vote(age: Int): Unit = println(s"$age: vote accepted")
  override def applyForBuspass(age: Int): Unit = println(s"$age: too young for a bus pass")
  override def conscript(age: Int): Unit = println("Here is your gun")
  override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card not granted")
}

class Pensioner() extends PersonState(){

  override def vote(age: Int): Unit = println(s"$age: vote accepted")
  override def applyForBuspass(age: Int): Unit = println(s"$age: pass granted")
  override def conscript(age: Int): Unit = println(s"$age: too old to be conscripted")
  override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card granted")
}

class Teenager() extends PersonState(){

  override def vote(age: Int): Unit = println(s"$age: too young to vote")
  override def applyForBuspass(age: Int): Unit = println(s"$age: pass granted")
  override def conscript(age: Int): Unit = println("Here is your gun")
  override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card not granted")
}



class Person:
  var age = 0
  var state: PersonState = new Child()

  def incrementAge() =
    age += 1
    if age == 13 then
      state = new Teenager()
    else if age == 18 then
      state = new Adult()
    else if age == 65 then
      state = new Pensioner()



  def canPerform(): Unit=
    state.vote(age)
    state.applyForBuspass(age)
    state.conscript(age)
    state.applyForMedicalCard(age)


@main def main(): Unit =
  val p = Person()
  for i <- 1 to 80 do
    p.incrementAge()
    p.canPerform()

