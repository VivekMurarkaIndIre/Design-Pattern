import scala.util.Random

enum Result:
  case Success, Failure

trait Strategy(val maxNumAttempts: Int) {

  val rng: Random = Random(64429)
  var numAttemptsSoFar = 0

  /**
   * method should be overriden by all strategy
   * with own implementation
   * @param lower starting value for range/runs
   * @param upper terminating value for range/runs
   * @param oracle Oracle object which verifies the guess
   * @return Result
   */
  def useStrategy(lower: Int, upper: Int, oracle: Oracle): Result


  /**
   * Default method to reset the counter for
   * number of attempts
   */
  def reset(): Unit =
    numAttemptsSoFar = 0

  /**
   * Default method for all strategy
   * @param guessedCorrectly
   * @return Result as Success is correct else Failure
   */
  def isSuccessfulGuess(guessedCorrectly: Boolean): Result=
    if guessedCorrectly then
    // println(s"playLinear: I guessed $num and won!")
      Result.Success
    else
      Result.Failure


}
