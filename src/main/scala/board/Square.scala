package board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 13:57 08.10.2020  
 */
class Square private (val upperRectangle: Rectangle, val lowerRectangle: Rectangle,
             val indexes: List[Int]) {
  override def toString: String = s"$upperRectangle$lowerRectangle"

  def withUpper(rectangle: Rectangle) = new Square(rectangle, lowerRectangle, indexes ::: rectangle.indexes)

  def withLower(rectangle: Rectangle) = new Square(upperRectangle, rectangle, indexes ::: rectangle.indexes)

  private def canEqual(other: Any): Boolean = other.isInstanceOf[Square]

  override def equals(other: Any): Boolean = other match {
    case that: Square =>
      (that canEqual this) &&
        upperRectangle == that.upperRectangle &&
        lowerRectangle == that.lowerRectangle
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(upperRectangle, lowerRectangle)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Square {
  def apply(upperRectangle: Rectangle = Rectangle(), lowerRectangle: Rectangle = Rectangle(),
            indexes: List[Int] = List()): Square = new Square(upperRectangle, lowerRectangle, indexes)
}
