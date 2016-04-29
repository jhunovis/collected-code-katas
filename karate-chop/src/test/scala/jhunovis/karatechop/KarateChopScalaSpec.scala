package jhunovis.karatechop

import jhunovis.karatechop.KarateChopScala.chop
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, PropSpec}

/** A ScalaTest specification for [[KarateChopScala]]. The test-data was transformed directly from the test-cases
  * provided by [[http://codekata.com/kata/kata02-karate-chop/]].
  *
  * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
  * @version $Revision$ $Date$ $Author$
  */
class KarateChopScalaSpec extends PropSpec with TableDrivenPropertyChecks with Matchers {

  property("Element not found") {
    val searchData = Table(
      ("value", "sorted data"     ),
      (      3, Array(1)          ),
      (      3, Array.empty[Int]  ),
      (      3, Array(1, 2)       ),
      (      0, Array(1, 3, 5)    ),
      (      2, Array(1, 3, 5)    ),
      (      4, Array(1, 3, 5)    ),
      (      6, Array(1, 3, 5)    ),
      (      0, Array(1, 3, 5, 7) ),
      (      2, Array(1, 3, 5, 7) ),
      (      4, Array(1, 3, 5, 7) ),
      (      6, Array(1, 3, 5, 7) ),
      (      8, Array(1, 3, 5, 7) )
    )

    forAll(searchData) {
      (value, data: Array[Int]) => {
        infoOnSearch(value, data)
        chop(value, data) should be (-1)
      }
    }

  }

  property("Element found") {
    val searchData = Table(
      ("value", "sorted data", "position"),
      (      1, Array(1)         , 0 ),
      (      1, Array(1, 3, 5)   , 0 ),
      (      3, Array(1, 3, 5)   , 1 ),
      (      5, Array(1, 3, 5)   , 2 ),
      (      1, Array(1, 3, 5, 7), 0 ),
      (      3, Array(1, 3, 5, 7), 1 ),
      (      5, Array(1, 3, 5, 7), 2 ),
      (      7, Array(1, 3, 5, 7), 3 )
    )

    forAll(searchData) {
      (value, data: Array[Int], position) => {
        infoOnSearch(value, data)
        chop(value, data) should be (position)
      }
    }

  }

  def infoOnSearch(value: Int, data: Array[Int]): Unit = {
    println(s"""Search for $value in [${data.mkString(", ")}]""")
  }

}
