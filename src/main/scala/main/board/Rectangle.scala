package main.board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 13:54 08.10.2020
 *
 * Class that represents a rectangle of two [[Cell]].
 *
 * @param first   first [[Cell]].
 * @param second  second [[Cell]].
 * @param indexes `List` of indexes.
 */
class Rectangle private(val first: Cell, val second: Cell, val indexes: List[Int]) {
  override def toString: String = s"$first$second"

  /**
   * Creates new Rectangle from current with new first cell.
   *
   * @param cell  new first cell.
   * @param index new cell index.
   * @return a new [[Rectangle]] instance.
   */
  def withFirst(cell: Cell, index: Int): Rectangle = Rectangle(cell, second, indexes ::: List(index))

  /**
   * Creates new Rectangle from current with new second cell.
   *
   * @param cell  new second cell.
   * @param index new cell index.
   * @return a new [[Rectangle]] instance.
   */
  def withSecond(cell: Cell, index: Int): Rectangle = Rectangle(first, cell, indexes ::: List(index))

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
   * Creates rectangle with cells and list of their indexes.
   *
   * @param first   first [[Cell]].
   * @param second  second [[Cell]].
   * @param indexes `List` of indexes.
   * @return a new [[Rectangle]] instance.
   */
  def apply(first: Cell = Cell(), second: Cell = Cell(), indexes: List[Int] = List()): Rectangle =
    new Rectangle(first, second, indexes)
}
