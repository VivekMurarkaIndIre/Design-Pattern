class SmartRandomStrategy (maxNumAttempts: Int) extends Strategy(maxNumAttempts) {

  override def useStrategy(lwr: Int, upr: Int, oracle: Oracle): Result =
    var lower = lwr
    var upper = upr
    var guessedCorrectly = false
    while (numAttemptsSoFar < maxNumAttempts && lower < upper && !guessedCorrectly) do
      var guess = rng.between(lower, upper)
      numAttemptsSoFar += 1
      var result = oracle.isThisTheNumber(guess)
      if result == Comparison.Correct then
        guessedCorrectly = true
      else if result == Comparison.LessThan then
        upper = guess - 1
      else // result == Assessment.GreaterThan then
        lower = guess + 1
      end if
    isSuccessfulGuess(guessedCorrectly)

}

