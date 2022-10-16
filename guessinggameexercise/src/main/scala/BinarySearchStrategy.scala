class BinarySearchStrategy(maxNumAttempts: Int) extends Strategy(maxNumAttempts) {

  override def useStrategy(lwr: Int, upr: Int, oracle: Oracle): Result =
    var lower = lwr
    var upper = upr
    var guessedCorrectly = false
    while (numAttemptsSoFar < maxNumAttempts && !guessedCorrectly) do
      var guess = (lower + upper) / 2
      numAttemptsSoFar += 1
      var result = oracle.isThisTheNumber(guess)
      if result == Comparison.Correct then
        guessedCorrectly = true
      else if result == Comparison.LessThan then
        upper = guess - 1
      else // result == Assessment.GreaterThan
        lower = guess + 1
      end if
    isSuccessfulGuess(guessedCorrectly)

}
