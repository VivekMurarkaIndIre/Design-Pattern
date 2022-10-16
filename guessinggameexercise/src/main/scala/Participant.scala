class Participant(maxNumAttempts: Int,numOfRuns: Int):
  var totalNumAttempts = 0
  var totalNumFailures = 0
  var oracle: Oracle = Oracle()
  def play(strategy: Strategy): Unit =

    var i=0
    for i <- 1 to numOfRuns do
      //println(totalNumAttempts)
      oracle.secretNumber = i
      strategy.reset()
      if strategy.useStrategy(1, numOfRuns,oracle) == Result.Success then
      // println(s"play randomly found ${i} in ${homer.numAttempts} attempts")
        totalNumAttempts += strategy.numAttemptsSoFar
      else
      // println(s"play randomly failed to find ${i} after ${homer.numAttempts} attempts")
        totalNumFailures += 1
    //println(s"playRandomly took on average ${totalNumAttempts / (numOfRuns - totalNumFailures)} attempts to succeed")
