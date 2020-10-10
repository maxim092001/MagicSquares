package board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 14:14 07.10.2020  
 */
class Cell private (val upperLeftValue: Int, val upperRightValue: Int,
           val lowerLeftValue: Int, val lowerRightValue: Int) {

  override def toString = s"$upperLeftValue $upperRightValue $lowerLeftValue $lowerRightValue\n"

  private def canEqual(other: Any): Boolean = other.isInstanceOf[Cell]

  override def equals(other: Any): Boolean = other match {
    case that: Cell =>
      (that canEqual this) &&
        upperLeftValue == that.upperLeftValue &&
        upperRightValue == that.upperRightValue &&
        lowerLeftValue == that.lowerLeftValue &&
        lowerRightValue == that.lowerRightValue
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(upperLeftValue, upperRightValue, lowerLeftValue, lowerRightValue)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Cell {
  def apply(upperLeftValue: Int = 0, upperRightValue: Int = 0,
            lowerLeftValue: Int = 0, lowerRightValue: Int = 0): Cell =
    new Cell(upperLeftValue, upperRightValue, lowerLeftValue, lowerRightValue)
}
