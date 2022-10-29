
class Participant ():
  var totalNumAttempts = 0
  var totalNumFailures = 0
  var name: String=""
  var age:Int= 0
  var maxNumAttempts:Int=0
  var numOfRuns:Int=0
  var strategyName: String=""
  //Auxiliary Constructor
  def this(name: String, age: Int,maxNumAttempts: Int,numOfRuns: Int, strategyName : String)={
    this()
    this.name = name
    this.age = age
    this.maxNumAttempts= maxNumAttempts
    this.numOfRuns=numOfRuns
    this.strategyName = strategyName
  }

  var oracle: Oracle = Oracle()
  def play(strategy: Strategy): Unit =

    var i=0
    for i <- 1 to numOfRuns do
      //println(totalNumAttempts)
      oracle.secretNumber = i
      strategy.reset()
      if strategy.useStrategy(1, numOfRuns,oracle) == Result.Success then
        totalNumAttempts += strategy.numAttemptsSoFar
      else
        totalNumFailures += 1

  def avgNumberOfAttempts(): Int =
   totalNumAttempts / (numOfRuns - totalNumFailures)


