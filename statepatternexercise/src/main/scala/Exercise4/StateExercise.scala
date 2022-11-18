package Exercise4

abstract class PersonState(person: Person){

  def transitionState(): Unit=
    if (person.age == 1) then
      person.changeState(new Child(person))

  def vote(age: Int): Unit
  def applyForBuspass(age: Int): Unit
  def conscript(age: Int): Unit
  def applyForMedicalCard(age: Int): Unit
}


class Child(person: Person) extends PersonState(person){

  override def transitionState(): Unit =
    if (person.age == 13) then
      person.changeState(new Teenager(person))

  override def vote(age: Int): Unit = println(s"$age: too young to vote")
  override def applyForBuspass(age: Int): Unit = println(s"$age: too young for a bus pass")
  override def conscript(age: Int): Unit = println(s"$age: too young to be conscripted")
  override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card granted")
}

class Adult(person: Person) extends PersonState(person){

  override def transitionState(): Unit =
    if (person.age == 65) then
      person.changeState(new Pensioner(person))
  override def vote(age: Int): Unit = println(s"$age: vote accepted")
  override def applyForBuspass(age: Int): Unit = println(s"$age: too young for a bus pass")
  override def conscript(age: Int): Unit = println("Here is your gun")
  override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card not granted")
}

class Pensioner(person: Person) extends PersonState(person){

  override def vote(age: Int): Unit = println(s"$age: vote accepted")
  override def applyForBuspass(age: Int): Unit = println(s"$age: pass granted")
  override def conscript(age: Int): Unit = println(s"$age: too old to be conscripted")
  override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card granted")
}

class Teenager(person: Person) extends PersonState(person){

  override def transitionState(): Unit =
    if (person.age == 18) then
      person.changeState(new Adult(person))
  override def vote(age: Int): Unit = println(s"$age: too young to vote")
  override def applyForBuspass(age: Int): Unit = println(s"$age: pass granted")
  override def conscript(age: Int): Unit = println("Here is your gun")
  override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card not granted")
}



class Person:
  var age = 0
  var state: PersonState = new Child(this)

  def changeState(state: PersonState) =
    this.state= state

  def incrementAge() =
    age += 1
    state.transitionState()



  def canPerform(): Unit=
    state.vote(age)
    state.applyForBuspass(age)
    state.conscript(age)
    state.applyForMedicalCard(age)


@main def hello: Unit =
  val p = Person()
  for i <- 1 to 80 do
    p.incrementAge()
    p.canPerform()

