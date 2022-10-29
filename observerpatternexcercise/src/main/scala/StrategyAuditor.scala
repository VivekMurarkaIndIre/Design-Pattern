import scala.annotation.switch

class StrategyAuditor extends Observer{

  override def notification(subject: Participant): Unit =

    var riggedOrNot = false;
    (subject.strategyName: @switch) match {
      case "RandomStrategy" => if (subject.avgNumberOfAttempts() < 68 ) riggedOrNot= true
      case "LinearStrategy" => if (subject.avgNumberOfAttempts() < 50 ) riggedOrNot= true
      case "SmartRandomStrategy" => if (subject.avgNumberOfAttempts() < 6 ) riggedOrNot= true
      case "BinarySearchStrategy" => if (subject.avgNumberOfAttempts() < 5 ) riggedOrNot= true
      case "ReverseLinearStrategy" => if (subject.avgNumberOfAttempts() < 50 ) riggedOrNot= true
      case "CheaterStrategy" => if (subject.avgNumberOfAttempts() == 1 ) riggedOrNot= true
    }

    if (riggedOrNot){
      println(s"Alarm!! The Participant ${subject.name} has possibly rigged the game")
    }else{
      println(s"Good Job!!Participant ${subject.name} is clean.")
    }


}
