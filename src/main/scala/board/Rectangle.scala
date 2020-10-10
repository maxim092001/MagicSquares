package board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 13:54 08.10.2020
 *
 * Class that represents a rectangle of two cells
 *
 * @param first first cell.
 * @param second second cell.
 * @param indexes list of indexes.
 */
class Rectangle private (val first: Cell, val second: Cell, val indexes: List[Int]) {
  override def toString: String = s"$first$second"

  def withFirst(newFirst: Cell, index: Int): Rectangle = Rectangle(newFirst, second, indexes ::: List(index))
  def withSecond(newSecond: Cell, index: Int): Rectangle = Rectangle(first, newSecond, indexes ::: List(index))

  private def canEqual(other: Any): Boolean = other.isInstanceOf[Rectangle]

  override def equals(other: Any): Boolean = other match {
    case that: Rectangle =>
      (that canEqual this) &&
        first == that.first &&
        second == that.second
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(first, second)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

/** Factory for [[Rectangle]] instances. */
object Rectangle {
  /**
   * Creates rectangle with two cells and list of their indexes.
   *
   * @param first first cell. Default is zero Cell.
   * @param second second cell. Default is zero Cell.
   * @param indexes list of indexes. Default is empty List.
   * @return a new Rectangle instance.
   */
  def apply(first: Cell = Cell(), second: Cell = Cell(), indexes: List[Int] = List()): Rectangle =
    new Rectangle(first, second, indexes)
}
