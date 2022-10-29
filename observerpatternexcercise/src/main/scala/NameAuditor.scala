import scala.collection.mutable.Map

class NameAuditor extends Observer{
  var nameOfParticipants = Map[String, Int]()
  override def notification(subject: Participant): Unit =
    if nameOfParticipants.contains(subject.name) then
       var originalCount = nameOfParticipants.apply(subject.name)
       if(originalCount >= 2 ) then
         println(s"Something is suspicious!! Participant ${subject.name} has won ${originalCount} times in a row")
       nameOfParticipants(subject.name) = originalCount+1
    else
      nameOfParticipants(subject.name)= 1

}
