class CheatStrategy (maxNumAttempts: Int) extends Strategy(maxNumAttempts) {

  override def useStrategy(lwr: Int, upr: Int, oracle: Oracle): Result =
    numAttemptsSoFar =1
    isSuccessfulGuess(true)


}
