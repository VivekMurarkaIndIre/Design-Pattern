import scala.collection.mutable.ListBuffer

object Main extends  Observable {
  val numOfRuns = 100

  def main(args: Array[String]): Unit =

    val startegyAuditor = StrategyAuditor()
    val ageAuditor = AgeAuditor()
    val nameAuditor = NameAuditor()

    //create anonymous function to add auditor in the list
    //and pass this to attach method
    attach(() => observers.addOne(ageAuditor))
    attach(() => observers.addOne(startegyAuditor))
    attach(() => observers.addOne(nameAuditor))

    val maxNumAttempts = numOfRuns * 2
    val homer = Participant("homer",28,maxNumAttempts, numOfRuns,"RandomStrategy")
    val homerStrategy = new RandomStrategy(maxNumAttempts)
    homer.play(homerStrategy)
    println(s"playRandomly took on average ${homer.avgNumberOfAttempts()} attempts to succeed")
    notifyObservers(homer)

    val bart = Participant("bart",36,maxNumAttempts = numOfRuns * 2, numOfRuns,"LinearStrategy")
    val bartStrategy = new LinearStrategy(maxNumAttempts)
    bart.play(bartStrategy)
    println(s"playLinear took on average ${bart.avgNumberOfAttempts()} attempts to succeed")
    notifyObservers(bart)

    val maggie = Participant("maggie",25,maxNumAttempts = numOfRuns / 2, numOfRuns,"SmartRandomStrategy")
    val maggieStrategy = new SmartRandomStrategy(maxNumAttempts)
    maggie.play(maggieStrategy)
    println(s"playSmartRandom took on average ${maggie.avgNumberOfAttempts()} attempts to succeed")
    notifyObservers(maggie)

    val lisa = Participant("lisa",32,maxNumAttempts = numOfRuns / 2, numOfRuns,"BinarySearchStrategy")
    val lisaStrategy = new BinarySearchStrategy(maxNumAttempts)
    lisa.play(lisaStrategy)
    println(s"playBinarySearch took on average ${lisa.avgNumberOfAttempts()} attempts to succeed")
    notifyObservers(lisa)

    val tom = Participant("tom",21,maxNumAttempts = numOfRuns * 2, numOfRuns,"ReverseLinearStrategy")
    val tomStrategy = new ReverseLinearStrategy(maxNumAttempts)
    tom.play(tomStrategy)
    println(s"playReverseLinear took on average ${tom.avgNumberOfAttempts()} attempts to succeed")
    notifyObservers(tom)

    //few more test participants
    //Age auditor alarms

    val jackOne = Participant("Jack", 17, maxNumAttempts = numOfRuns * 2, numOfRuns, "ReverseLinearStrategy")
    val jackOneStrategy = new ReverseLinearStrategy(maxNumAttempts)
    jackOne.play(jackOneStrategy)
    notifyObservers(jackOne)

    val jackTwo = Participant("Jack", 75, maxNumAttempts = numOfRuns * 2, numOfRuns, "ReverseLinearStrategy")
    val jackTwoStrategy = new ReverseLinearStrategy(maxNumAttempts)
    jackTwo.play(jackTwoStrategy)
    notifyObservers(jackTwo)

    //name auditor alarms
    val jackThree = Participant("Jack", 25, maxNumAttempts = numOfRuns * 2, numOfRuns, "ReverseLinearStrategy")
    val jackThreeStrategy = new ReverseLinearStrategy(maxNumAttempts)
    jackThree.play(jackThreeStrategy)
    notifyObservers(jackThree)

    //strategy auditor alarm
    val cheater = Participant("Cheater", 25, maxNumAttempts = 1, 1, "CheaterStrategy")
    val cheaterStrategy = new CheatStrategy(maxNumAttempts)
    cheater.play(cheaterStrategy)
    println(s"cheater took on average ${cheater.avgNumberOfAttempts()} attempts to succeed")
    notifyObservers(cheater)

    detach(() => observers.clear())


}


