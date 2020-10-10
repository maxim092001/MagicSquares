package board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 13:54 08.10.2020  
 */
class Rectangle private (val first: Cell, val second: Cell, val indexes: List[Int]) {
  override def toString: String = s"$first$second"

  def withFirst(newFirst: Cell, index: Int): Rectangle = Rectangle(newFirst, second, indexes ::: List(index))
  def withSecond(newSecond: Cell, index: Int): Rectangle = Rectangle(first, newSecond, indexes ::: List(index))

  def canEqual(other: Any): Boolean = other.isInstanceOf[Rectangle]

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

object Rectangle {
  def apply(first: Cell = Cell(), second: Cell = Cell(), indexes: List[Int] = List()): Rectangle =
    new Rectangle(first, second, indexes)
}
