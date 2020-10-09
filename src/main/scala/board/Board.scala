package board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 14:16 08.10.2020  
 */
class Board(val centreSquare: Square = new Square(), val upperRectangle: Rectangle = new Rectangle(), val rightRectangle: Rectangle = new Rectangle(),
            val lowerRectangle: Rectangle = new Rectangle(), val leftRectangle: Rectangle = new Rectangle(), val indexes: List[Int] = List()) {

  def this(board: Board) = {
    this(board.centreSquare, board.upperRectangle, board.rightRectangle, board.lowerRectangle, board.leftRectangle, board.indexes)
  }

  def withLeft(left: Rectangle): Board = {
    new Board(centreSquare, upperRectangle, rightRectangle, lowerRectangle, left, indexes ::: left.indexes)
  }

  def withRight(right: Rectangle): Board = {
    new Board(centreSquare, upperRectangle, right, lowerRectangle, leftRectangle, indexes ::: right.indexes)
  }

  def withUpper(upper: Rectangle): Board = {
    new Board(centreSquare, upper, rightRectangle, lowerRectangle, leftRectangle, indexes ::: upper.indexes)
  }

  def withLower(lower: Rectangle): Board = {
    new Board(centreSquare, upperRectangle, rightRectangle, lower, leftRectangle, indexes ::: lower.indexes)
  }

  def withCenter(center: Square): Board = {
    new Board(center, upperRectangle, rightRectangle, lowerRectangle, leftRectangle, indexes ::: center.indexes)
  }

  override def toString: String = s"$upperRectangle${leftRectangle.first}${centreSquare.upperRectangle}${rightRectangle.first}" +
    s"${leftRectangle.second}${centreSquare.lowerRectangle}${rightRectangle.second}$lowerRectangle"


  def canEqual(other: Any): Boolean = other.isInstanceOf[Board]

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
