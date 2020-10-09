package board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 14:14 07.10.2020  
 */
class Cell(val upperLeftValue: Int = 0, val upperRightValue: Int = 0,
           val lowerLeftValue: Int = 0, val lowerRightValue: Int = 0) {

  override def toString = s"$upperLeftValue $upperRightValue $lowerLeftValue $lowerRightValue\n"


  def canEqual(other: Any): Boolean = other.isInstanceOf[Cell]

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
