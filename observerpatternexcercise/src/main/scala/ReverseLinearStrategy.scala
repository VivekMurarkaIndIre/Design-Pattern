class ReverseLinearStrategy(maxNumAttempts: Int) extends Strategy(maxNumAttempts) {

  override def useStrategy(lower: Int, upper: Int, oracle: Oracle): Result =
    var guess = upper
    var guessedCorrectly = false
    while (numAttemptsSoFar < maxNumAttempts && !guessedCorrectly) do
      guess -= 1
      numAttemptsSoFar += 1
      if oracle.isThisTheNumber(guess) == Comparison.Correct then
        guessedCorrectly = true
    end while
    isSuccessfulGuess(guessedCorrectly)

}
