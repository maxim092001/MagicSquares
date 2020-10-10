package board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 14:16 08.10.2020  
 */
class Board private (val centreSquare: Square, val upperRectangle: Rectangle,
                    val rightRectangle: Rectangle,
                    val lowerRectangle: Rectangle, val leftRectangle: Rectangle,
                    val indexes: List[Int]) {

  private def this(board: Board) =
    this(board.centreSquare, board.upperRectangle, board.rightRectangle, board.lowerRectangle, board.leftRectangle, board.indexes)

  def withLeft(left: Rectangle): Board =
    Board(centreSquare, upperRectangle, rightRectangle, lowerRectangle, left, indexes ::: left.indexes)

  def withRight(right: Rectangle): Board =
    Board(centreSquare, upperRectangle, right, lowerRectangle, leftRectangle, indexes ::: right.indexes)

  def withUpper(upper: Rectangle): Board =
    Board(centreSquare, upper, rightRectangle, lowerRectangle, leftRectangle, indexes ::: upper.indexes)

  def withLower(lower: Rectangle): Board =
    Board(centreSquare, upperRectangle, rightRectangle, lower, leftRectangle, indexes ::: lower.indexes)

  def withCenter(center: Square): Board =
    Board(center, upperRectangle, rightRectangle, lowerRectangle, leftRectangle, indexes ::: center.indexes)

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

object Board {
  def apply(centreSquare: Square = Square(), upperRectangle: Rectangle = Rectangle(),
            rightRectangle: Rectangle = Rectangle(), lowerRectangle: Rectangle = Rectangle(),
            leftRectangle: Rectangle = Rectangle(), indexes: List[Int] = List()): Board =
    new Board(centreSquare, upperRectangle, rightRectangle, lowerRectangle, leftRectangle, indexes)

  def apply(board: Board): Board = new Board(board)
}
