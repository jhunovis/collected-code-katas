package jhunovis.karatechop

/**
  * Karate Chop
  * ===========
  *
  * Write a binary chop method that takes an integer search target and a sorted array of integers.
  * It should return the integer index of the target in the array, or -1 if the target is not in the array.
  *
  * You can assume that the array has less than 100,000 elements.
  * For the purposes of this Kata, time and memory performance are not issues (assuming the chop terminates before
  * you get bored and kill it, and that you have enough RAM to run it).
  *
  * from: [[http://codekata.com/kata/kata02-karate-chop/]]
  *
  * Entry point is [[jhunovis.karatechop.KarateChopScala.chop(Int, Array[Int]):Int]]
  *
  * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
  * @version $Revision$ $Date$ $Author$
  */
class KarateChopScala(private val sortedData: Array[Int]) {

  def pivot(range: Range): Int = {
    range.start + ((range.end - range.start) / 2)
  }

  def find(range: Range, value: Int): Int = {
    println(s"Searching $value between index ${range.start} and ${range.end}")
    if (range.isEmpty) -1
    else {
      val pivot: Int = this.pivot(range)
      value compareTo sortedData(pivot) match {
        case -1 => find(Range(range.start, pivot), value)
        case  0 => pivot
        case  1 => find(Range(pivot+1, range.end), value)
      }
    }
  }

  def find(value: Int): Int = {
    find(sortedData.indices, value)
  }
}

object KarateChopScala {
  /** Search for a given number in a sorted array of numbers.
    *
    * @param searchValue the value to search
    * @param sortedData the data in which to search. Must be sorted.
    * @return the index of the search value in the data, or -1 if it is not present.
    */
  def chop(searchValue: Int, sortedData: Array[Int]): Int = {
    new KarateChopScala(sortedData).find(searchValue)
  }
}
