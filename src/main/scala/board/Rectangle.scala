package board

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 13:54 08.10.2020  
 */
class Rectangle(val first: Cell = new Cell(), val second: Cell = new Cell(), val indexes: List[Int] = List()) {
  override def toString: String = s"$first$second"

  def withFirst(newFirst: Cell) = new Rectangle(newFirst, second)
  def withSecond(newSecond: Cell) = new Rectangle(first, newSecond)


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
