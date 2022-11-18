package Exercise5

import Exercise5.PersonState.{Child, Teenager}


abstract class PersonState(){

  def transitionState(age: Int): PersonState = Child




  def vote(age: Int): Unit
  def applyForBuspass(age: Int): Unit
  def conscript(age: Int): Unit
  def applyForMedicalCard(age: Int): Unit
}

object PersonState{

  object Child extends PersonState{

    override def transitionState(age: Int): PersonState = if (age == 13) Teenager else this

    override def vote(age: Int): Unit = println(s"$age: too young to vote")

    override def applyForBuspass(age: Int): Unit = println(s"$age: too young for a bus pass")

    override def conscript(age: Int): Unit = println(s"$age: too young to be conscripted")

    override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card granted")

  }

  object Adult extends PersonState {

    override def transitionState(age: Int): PersonState = if (age == 65) Pensioner else this

    override def vote(age: Int): Unit = println(s"$age: vote accepted")

    override def applyForBuspass(age: Int): Unit = println(s"$age: too young for a bus pass")

    override def conscript(age: Int): Unit = println("Here is your gun")

    override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card not granted")
  }

  object Pensioner extends PersonState{

    override def transitionState(age: Int): PersonState = Pensioner

    override def vote(age: Int): Unit = println(s"$age: vote accepted")

    override def applyForBuspass(age: Int): Unit = println(s"$age: pass granted")

    override def conscript(age: Int): Unit = println(s"$age: too old to be conscripted")

    override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card granted")
  }

  object  Teenager extends PersonState{

    override def transitionState(age: Int): PersonState = if (age == 18) Adult else this
    override def vote(age: Int): Unit = println(s"$age: too young to vote")

    override def applyForBuspass(age: Int): Unit = println(s"$age: pass granted")

    override def conscript(age: Int): Unit = println("Here is your gun")

    override def applyForMedicalCard(age: Int): Unit = println(s"$age: Medical Card not granted")
  }

}





class Person:
  var age = 0
  var state :PersonState = Child


  def incrementAge() =
    age += 1
    state = state.transitionState(age)



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

