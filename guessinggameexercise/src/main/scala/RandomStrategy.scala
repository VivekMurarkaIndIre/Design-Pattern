class RandomStrategy(maxNumAttempts: Int) extends Strategy(maxNumAttempts) {

  override def useStrategy(lower: Int, upper: Int, oracle: Oracle): Result =
    var guessedCorrectly = false
    while (numAttemptsSoFar < maxNumAttempts && !guessedCorrectly) do
      var guess = rng.between(lower, upper)
      numAttemptsSoFar += 1
      if oracle.isThisTheNumber(guess) == Comparison.Correct then
        guessedCorrectly = true
    end while
    isSuccessfulGuess(guessedCorrectly)
}
