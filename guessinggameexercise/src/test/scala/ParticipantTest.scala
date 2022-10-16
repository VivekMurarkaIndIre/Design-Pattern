class ParticipantTest extends munit.FunSuite {

  test("Guesses 5 in 11 attempts using playRandomly") {
    val oracle = Oracle()
    oracle.secretNumber = 5
    val participant = Participant(oracle, maxNumAttempts = 100)
    RandomStrategy strategy = new RandomStrategy(maxNumAttempts)
    participant.play()
    val result = participant.playRandomly(0, 10)
    assertEquals(result, Result.Success)
    assertEquals(participant.numAttemptsSoFar, 11)
  }

  test("Fails to guess 5 in max 10 attempts using playRandomly") {
    val oracle = Oracle()
    oracle.secretNumber = 5
    val participant = Participant(oracle, maxNumAttempts = 10)
    val result = participant.playRandomly(0, 10)
    assertEquals(result, Result.Failure)
    assertEquals(participant.numAttemptsSoFar, 10)
  }

  test("Guesses 7 in 8 attempts using playLinear") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val participant = Participant(oracle, maxNumAttempts = 10)
    val result = participant.playLinear(0)
    assertEquals(result, Result.Success)
    assertEquals(participant.numAttemptsSoFar, 8)
  }

  test("Fails to guesses 7 in 7 attempts using playLinear") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val participant = Participant(oracle, maxNumAttempts = 7)
    val result = participant.playLinear(0)
    assertEquals(result, Result.Failure)
    assertEquals(participant.numAttemptsSoFar, 7)
  }

  test("Guesses 7 in 3 attempts using playSmartRandom") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val participant = Participant(oracle, maxNumAttempts = 10)
    val result = participant.playSmartRandom(0, 10)
    assertEquals(result, Result.Success)
    assertEquals(participant.numAttemptsSoFar, 3)
  }

  test("Fails to guesses 7 in 2 attempts using playSmartRandom") {
    val oracle = Oracle()
    oracle.secretNumber = 7
    val participant = Participant(oracle, maxNumAttempts = 2)
    val result = participant.playSmartRandom(0, 10)
    assertEquals(result, Result.Failure)
    assertEquals(participant.numAttemptsSoFar, 2)
  }

  test("Fails to guesses 57 in 5 attempts using playBinarySearch") {
    val oracle = Oracle()
    oracle.secretNumber = 57
    val participant = Participant(oracle, maxNumAttempts = 5)
    val result = participant.playBinarySearch(0, 100)
    assertEquals(result, Result.Failure)
    assertEquals(participant.numAttemptsSoFar, 5)
  }
}

