class AgeAuditor extends Observer{

  override def notification(subject: Participant): Unit =
    if subject.age < 18 then
      println(s"The participant ${subject.name} is under age to play the game. Legal age is inbetween 18 to 60")
    else if subject.age > 60 then
      println(s"The participant ${subject.name} is above age to play the game. Legal age is inbetween 18 to 60")
    else
      println(s"The participant ${subject.name}'s age lies under legal age")

}
