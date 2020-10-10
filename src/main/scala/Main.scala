import board._

import scala.io.StdIn.readLine

/**
 * @author Grankin Maxim (maximgran@gmail.com) at 14:14 07.10.2020
 */
object Main extends App {

  val cells: Array[Cell] = (for (i <- 0 to 11) yield
    readLine().split("\\s+").map(_.toInt).toList
    ).toList.map { case ul :: ur :: ll :: lr :: Nil => Cell(ul, ur, ll, lr) }.toArray

  val boards: List[Board] =
    (for (i <- 0 to 11; j <- 0 to 11; k <- 0 to 11; f <- 0 to 11 if isUnique(i, j, k, f) &&
      isGreen(List(cells(i), cells(j), cells(k), cells(f)),
        List(_.lowerRightValue, _.lowerLeftValue, _.upperRightValue, _.upperLeftValue))) yield
      Board().withCenter(Square().withUpper(Rectangle().withFirst(cells(i), i).withSecond(cells(j), j))
        .withLower(Rectangle().withFirst(cells(k), k).withSecond(cells(f), f)))).toList.distinct

  val fullyFilledBoards = findBoundaries(cells, boards)

  fullyFilledBoards.filter(board => checkBoardForYellow(board))

  if (fullyFilledBoards.isEmpty) println("There is no satisfying permutation of the given cells")
  else fullyFilledBoards.foreach(board => println(board))

  /**
   * Checks that the board satisfies the yellow cell condition.
   *
   * @param board check board.
   * @return `true` if board satisfies, `false` otherwise.
   */
  private def checkBoardForYellow(board: Board): Boolean = {
    isYellow(List(board.upperRectangle.first, board.upperRectangle.second),
      List(_.upperRightValue, _.upperLeftValue)) &&
      isYellow(List(board.upperRectangle.second, board.centreSquare.upperRectangle.second, board.rightRectangle.first),
        List(_.lowerRightValue, _.upperRightValue, _.upperLeftValue)) &&
      isYellow(List(board.leftRectangle.first, board.leftRectangle.second),
        List(_.lowerRightValue, _.upperRightValue)) &&
      isYellow(List(board.leftRectangle.second, board.centreSquare.lowerRectangle.second, board.lowerRectangle.second),
        List(_.lowerLeftValue, _.lowerRightValue, _.upperRightValue)) &&
      isYellow(List(board.lowerRectangle.first, board.lowerRectangle.second),
        List(_.lowerRightValue, _.lowerLeftValue)) &&
      isYellow(List(board.lowerRectangle.first, board.centreSquare.lowerRectangle.first, board.leftRectangle.second),
        List(_.upperLeftValue, _.lowerLeftValue, _.lowerRightValue)) &&
      isYellow(List(board.leftRectangle.first, board.leftRectangle.second),
        List(_.lowerLeftValue, _.upperLeftValue)) &&
      isYellow(List(board.leftRectangle.first, board.centreSquare.upperRectangle.first, board.upperRectangle.first),
        List(_.upperRightValue, _.upperLeftValue, _.lowerLeftValue))
  }

  /**
   * Adds all rectangles to each board.
   *
   * @param cells array of cells.
   * @param boards list of boards.
   * @return List of new distinct boards with all rectangles added.
   */
  private def findBoundaries(cells: Array[Cell], boards: List[Board]): List[Board] = {
    var res: List[Board] = boards

    res = boundary(cells, res, List(_.centreSquare.upperRectangle.first, _.centreSquare.upperRectangle.second),
      List(_.upperRightValue, _.upperLeftValue, _.lowerRightValue, _.lowerLeftValue), _.withUpper)

    res = boundary(cells, res, List(_.centreSquare.upperRectangle.second, _.centreSquare.lowerRectangle.second),
      List(_.lowerRightValue, _.upperRightValue, _.lowerLeftValue, _.upperLeftValue), _.withRight)

    res = boundary(cells, res, List(_.centreSquare.lowerRectangle.first, _.centreSquare.lowerRectangle.second),
      List(_.lowerRightValue, _.lowerLeftValue, _.upperRightValue, _.upperLeftValue), _.withLower)

    boundary(cells, res, List(_.centreSquare.upperRectangle.first, _.centreSquare.lowerRectangle.first),
      List(_.lowerLeftValue, _.upperLeftValue, _.lowerRightValue, _.upperRightValue), _.withLeft)
  }

  /**
   * Finds all possible matching rectangles and adds them to all boards.
   *
   * @param cells array of cells.
   * @param boards list of boards.
   * @param boardMappers list of mappers for boards. Each mapper matches boundary cells.
   * @param boardCellMappers list of mapper for cells. Each mapper matches required values from the cells.
   * @param sideMapper mapper to match which Rectangle we want to add.
   * @return List of new distinct boards with added rectangles.
   */
  private def boundary(cells: Array[Cell], boards: List[Board], boardMappers: List[Board => Cell],
                       boardCellMappers: List[Cell => Int], sideMapper: Board => Rectangle => Board): List[Board] =
    (for (board <- boards) yield
      addRectangles(cells, board, boardMappers.map(f => f(board)), boardCellMappers, sideMapper)).flatten.distinct

  /**
   * Finds all possible matching rectangles and adds them to the board.
   *
   * @param cells array of cells.
   * @param board current board.
   * @param boardCells board cells to which a rectangle is added.
   * @param boardCellMappers list of mappers for cells. Each mapper matches required values from the cells.
   * @param sideMapper mapper to match which Rectangle we want to add.
   * @return List of new boards with added rectangles.
   */
  private def addRectangles(cells: Array[Cell], board: Board, boardCells: List[Cell],
                            boardCellMappers: List[Cell => Int], sideMapper: Board => Rectangle => Board)
  : List[Board] =
    (for (i <- 0 to 11; j <- 0 to 11 if isUnique(i, j) &&
      !(board.indexes.contains(i) || board.indexes.contains(j)) &&
      isGreen(boardCells ::: List(cells(i), cells(j)), boardCellMappers))
      yield sideMapper(board)(Rectangle(cells(i), cells(j), List(i, j)))).toList

  /**
   * Verifies that the sum of the values matches the green condition.
   *
   * @param cells list of cells.
   * @param cellMappers list of mappers for cells. Each mapper matches required values from the cells.
   * @return `true` if sum equal ten, `false` otherwise
   */
  private def isGreen(cells: List[Cell], cellMappers: List[Cell => Int]) = compareSum(cells, cellMappers, _ == 10)

  /**
   * Verifies that the sum of the values matches the yellow condition.
   *
   * @param cells list of cells.
   * @param cellMappers list of mappers for cells. Each mapper matches required values from the cells.
   * @return `true` if sum less or equal ten, `false` otherwise
   */
  private def isYellow(cells: List[Cell], cellMappers: List[Cell => Int]) = compareSum(cells, cellMappers, _ <= 10)

  /**
   * Verifies if sum of values in cells satisfies the predicate.
   *
   * @param cells list of cells.
   * @param cellMappers list of mappers for cells. Each mapper matches required values from the cells.
   * @param cmp function to compare sum.
   * @return `true` if sum satisfies the predicate, `false` otherwise.
   */
  private def compareSum(cells: List[Cell], cellMappers: List[Cell => Int], cmp: Int => Boolean) =
    cmp(cells.zip(cellMappers).map { case (c, f) => f(c) }.sum)

  /**
   * Verifies if a set of numbers consists only of unique elements.
   *
   * @param arr set of numbers
   * @return `true` if unique, `false` otherwise.
   */
  private def isUnique(arr: Int*) = arr.distinct.size == arr.size
}
