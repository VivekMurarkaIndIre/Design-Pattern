class StrategyTest extends munit.FunSuite {

  test("Guesses 5 in 11 attempts using playRandomly") {
    val oracle = Oracle()
    oracle.secretNumber = 5
    val strategy = new RandomStrategy(maxNumAttempts = 100)
    val result = strategy.useStrategy(0,10,oracle)
    assertEquals(result, Result.Success)
    assertEquals(strategy.numAttemptsSoFar, 11)
  }

  test("Fails to guess 5 in max 10 attempts using playRandomly") {
    val oracle = Oracle()
    oracle.secretNumber = 5
    val strategy = new RandomStrategy(maxNumAttempts = 10)
    val result = strategy.useStrategy(0, 10,oracle)
    assertEquals(result, Result.Failure)
    assertEquals(strategy.numAttemptsSoFar, 10)
  }

  test("Guesses 7 in 8 attempts using playLinear") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val strategy = new LinearStrategy(maxNumAttempts = 10)
    val result = strategy.useStrategy(0, 10, oracle)
    assertEquals(result, Result.Success)
    assertEquals(strategy.numAttemptsSoFar, 8)
  }

  test("Fails to guesses 7 in 7 attempts using playLinear") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val strategy = new LinearStrategy(maxNumAttempts = 7)
    val result = strategy.useStrategy(0, 10, oracle)
    assertEquals(result, Result.Failure)
    assertEquals(strategy.numAttemptsSoFar, 7)
  }

  test("Guesses 7 in 3 attempts using playSmartRandom") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val strategy = new SmartRandomStrategy(maxNumAttempts = 10)
    val result = strategy.useStrategy(0, 10, oracle)
    assertEquals(result, Result.Success)
    assertEquals(strategy.numAttemptsSoFar, 3)
  }

  test("Fails to guesses 7 in 2 attempts using playSmartRandom") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val strategy = new SmartRandomStrategy(maxNumAttempts = 2)
    val result = strategy.useStrategy(0, 10, oracle)
    assertEquals(result, Result.Failure)
    assertEquals(strategy.numAttemptsSoFar, 2)
  }

  test("Fails to guesses 57 in 5 attempts using playBinarySearch") {
    val oracle = Oracle()
    oracle.secretNumber = 57
    val strategy = new BinarySearchStrategy(maxNumAttempts = 5)
    val result = strategy.useStrategy(0, 100, oracle)
    assertEquals(result, Result.Failure)
    assertEquals(strategy.numAttemptsSoFar, 5)
  }

  test("Success to guesses 50 in 1 attempts using playBinarySearch") {
    val oracle = Oracle()
    oracle.secretNumber = 50
    val strategy = new BinarySearchStrategy(maxNumAttempts = 1)
    val result = strategy.useStrategy(0, 100, oracle)
    assertEquals(result, Result.Success)
    assertEquals(strategy.numAttemptsSoFar, 1)
  }

  test("Guesses 7 in 4 attempts using playLinear") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val strategy = new ReverseLinearStrategy(maxNumAttempts = 3)
    val result = strategy.useStrategy(0, 10, oracle)
    assertEquals(result, Result.Success)
    assertEquals(strategy.numAttemptsSoFar, 3)
  }

  test("Fails to guesses 3 in 7 attempts using playLinear") {
    val oracle = Oracle()
    oracle.secretNumber = 3
    val strategy = new ReverseLinearStrategy(maxNumAttempts = 6)
    val result = strategy.useStrategy(0, 10, oracle)
    assertEquals(result, Result.Failure)
    assertEquals(strategy.numAttemptsSoFar, 6)
  }


}

