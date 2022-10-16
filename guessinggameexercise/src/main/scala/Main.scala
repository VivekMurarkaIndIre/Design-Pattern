val numOfRuns = 100
@main def main: Unit =
  val maxNumAttempts = numOfRuns * 2
  val homer = Participant(maxNumAttempts,numOfRuns)
  val homerStrategy = new RandomStrategy(maxNumAttempts)
  homer.play(homerStrategy)
  println(s"playRandomly took on average ${homer.totalNumAttempts / (numOfRuns - homer.totalNumFailures)} attempts to succeed")

  val bart = Participant( maxNumAttempts = numOfRuns * 2,numOfRuns)
  val bartStrategy = new LinearStrategy(maxNumAttempts)
  bart.play(bartStrategy)
  println(s"playLinear took on average ${bart.totalNumAttempts / (numOfRuns - bart.totalNumFailures)} attempts to succeed")

  val maggie = Participant(maxNumAttempts = numOfRuns / 2,numOfRuns)
  val maggieStrategy = new SmartRandomStrategy(maxNumAttempts)
  maggie.play(maggieStrategy)
  println(s"playSmartRandom took on average ${maggie.totalNumAttempts / (numOfRuns - maggie.totalNumFailures)} attempts to succeed")

  val lisa = Participant( maxNumAttempts = numOfRuns / 2,numOfRuns)
  val lisaStrategy = new BinarySearchStrategy(maxNumAttempts)
  lisa.play(lisaStrategy)
  println(s"playBinarySearch took on average ${lisa.totalNumAttempts / (numOfRuns - lisa.totalNumFailures)} attempts to succeed")

  val tom = Participant( maxNumAttempts = numOfRuns * 2,numOfRuns)
  val tomStrategy = new ReverseLinearStrategy(maxNumAttempts)
  tom.play(tomStrategy)
  println(s"playReverseLinear took on average ${tom.totalNumAttempts / (numOfRuns - tom.totalNumFailures)} attempts to succeed")



