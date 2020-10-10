package main.board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 13:57 08.10.2020
 *
 * Represents square of two [[Rectangle]].
 * @param upperRectangle upper [[Rectangle]].
 * @param lowerRectangle lower [[Rectangle]].
 * @param indexes        `List` of indexes.
 */
class Square private(val upperRectangle: Rectangle, val lowerRectangle: Rectangle,
                     val indexes: List[Int]) {
  override def toString: String = s"$upperRectangle$lowerRectangle"

  /**
   * Creates new [[Square]] with new upper [[Rectangle]].
   *
   * @param rectangle new upper [[Rectangle]].
   * @return a new [[Square]] instance.
   */
  def withUpper(rectangle: Rectangle): Square = new Square(rectangle, lowerRectangle, indexes ::: rectangle.indexes)

  /**
   * Creates new [[Square]] with new lower rectangle.
   *
   * @param rectangle new lower rectangle.
   * @return a new [[Square]] instance.
   */
  def withLower(rectangle: Rectangle): Square = new Square(upperRectangle, rectangle, indexes ::: rectangle.indexes)

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

/** Factory for [[Square]] instances. */
object Square {

  /**
   * Create new [[Square]] instance with rectangles and their indexes.
   *
   * @param upperRectangle upper [[Rectangle]].
   * @param lowerRectangle lower [[Rectangle]].
   * @param indexes        `List` of indexes.
   * @return a new [[Square]] instance.
   */
  def apply(upperRectangle: Rectangle = Rectangle(), lowerRectangle: Rectangle = Rectangle(),
            indexes: List[Int] = List()): Square = new Square(upperRectangle, lowerRectangle, indexes)
}
