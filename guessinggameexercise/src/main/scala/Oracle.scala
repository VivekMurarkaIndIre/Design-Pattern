enum Comparison:
  case Correct, GreaterThan, LessThan

// Knows the number the participants are trying to guess
class Oracle:
  var secretNumber: Int = 0
  def isThisTheNumber(num: Int): Comparison =
    if num == secretNumber then
      Comparison.Correct
    else if num > secretNumber then
      Comparison.LessThan
    else
      Comparison.GreaterThan
    end if
