package main.board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 14:16 08.10.2020
 *
 * Represents board with centre, upper rectangle, lower rectangle, left rectangle and right rectangle.
 *
 * @param centreSquare   centre [[Square]].
 * @param upperRectangle upper [[Rectangle]].
 * @param rightRectangle right [[Rectangle]].
 * @param lowerRectangle lower [[Rectangle]].
 * @param leftRectangle  left [[Rectangle]].
 * @param indexes        `List` of indexes.
 */
class Board private(val centreSquare: Square, val upperRectangle: Rectangle,
                    val rightRectangle: Rectangle,
                    val lowerRectangle: Rectangle, val leftRectangle: Rectangle,
                    val indexes: List[Int]) {

  /**
   * Creates new board with new left rectangle.
   *
   * @param left new left rectangle.
   * @return a new [[Board]] instance.
   */
  def withLeft(left: Rectangle): Board =
    Board(centreSquare, upperRectangle, rightRectangle, lowerRectangle, left, indexes ::: left.indexes)

  /**
   * Creates new board with new right rectangle.
   *
   * @param right new right rectangle.
   * @return a new [[Board]] instance.
   */
  def withRight(right: Rectangle): Board =
    Board(centreSquare, upperRectangle, right, lowerRectangle, leftRectangle, indexes ::: right.indexes)

  /**
   * Creates new board with new upper rectangle.
   *
   * @param upper new upper rectangle.
   * @return a new [[Board]] instance.
   */
  def withUpper(upper: Rectangle): Board =
    Board(centreSquare, upper, rightRectangle, lowerRectangle, leftRectangle, indexes ::: upper.indexes)

  /**
   * Creates new board with new lower rectangle.
   *
   * @param lower new lower rectangle.
   * @return a new [[Board]] instance.
   */
  def withLower(lower: Rectangle): Board =
    Board(centreSquare, upperRectangle, rightRectangle, lower, leftRectangle, indexes ::: lower.indexes)

  /**
   * Creates new board with new centre square.
   *
   * @param centre new centre square.
   * @return a new [[Board]] instance.
   */
  def withCentre(centre: Square): Board =
    Board(centre, upperRectangle, rightRectangle, lowerRectangle, leftRectangle, indexes ::: centre.indexes)

  override def toString: String = s"$upperRectangle${leftRectangle.first}${centreSquare.upperRectangle}${rightRectangle.first}" +
    s"${leftRectangle.second}${centreSquare.lowerRectangle}${rightRectangle.second}$lowerRectangle"

  private def canEqual(other: Any): Boolean = other.isInstanceOf[Board]

  override def equals(other: Any): Boolean = other match {
    case that: Board =>
      (that canEqual this) &&
        centreSquare == that.centreSquare &&
        upperRectangle == that.upperRectangle &&
        rightRectangle == that.rightRectangle &&
        lowerRectangle == that.lowerRectangle &&
        leftRectangle == that.leftRectangle
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(centreSquare, upperRectangle, rightRectangle, lowerRectangle, leftRectangle)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

/** Factory for [[Board]] instances. */
object Board {

  /**
   * Creates new [[Board]] with centre square, lower, upper, left, and right rectangle.
   *
   * @param centreSquare   centre [[Square]].
   * @param upperRectangle upper [[Rectangle]].
   * @param rightRectangle right [[Rectangle]].
   * @param lowerRectangle lower [[Rectangle]].
   * @param leftRectangle  left [[Rectangle]].
   * @param indexes        `List` of indexes.
   * @return a new [[Board]] instance.
   */
  def apply(centreSquare: Square = Square(), upperRectangle: Rectangle = Rectangle(),
            rightRectangle: Rectangle = Rectangle(), lowerRectangle: Rectangle = Rectangle(),
            leftRectangle: Rectangle = Rectangle(), indexes: List[Int] = List()): Board =
    new Board(centreSquare, upperRectangle, rightRectangle, lowerRectangle, leftRectangle, indexes)
}
