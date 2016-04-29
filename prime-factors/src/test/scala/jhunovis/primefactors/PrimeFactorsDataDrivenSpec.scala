package jhunovis.primefactors

import jhunovis.primefactors.PrimeFactors.primeFactors
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{PropSpec, ShouldMatchers}

/**
  * A data-driven unit-test for [[PrimeFactors]].
  *
  * This test mixes in [[TableDrivenPropertyChecks]] instead of [[org.scalatest.prop.PropertyChecks]] on purpose
  * because the latter depends on [[org.scalatest.prop.GeneratorDrivenPropertyChecks]] which in turn depends
  * one the [[http://www.scalacheck.org ScalaCheck]] library. I could not get the two to work together though:
  * the execution always failed with a class incompatibility exception.
  *
  * Note also, that this class is annotated to run with [[JUnitRunner]]. This is not required for IDEA but for Gradle
  * which otherwise would not pick up the test-cases.
  *
  * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
  * @version $Revision$ $Date$ $Author$
  */
@RunWith(classOf[JUnitRunner])
class PrimeFactorsDataDrivenSpec extends PropSpec with TableDrivenPropertyChecks with ShouldMatchers {

  property("Prime number factor to themselves") {
    val primeNumbers = Table("prime number", 1, 2)

    forAll(primeNumbers) {
      primeNumber => primeFactors(primeNumber) should
        equal (List(primeNumber))
    }
  }

  property("Non-prime numbers factor to their prime factors") {
    val nonPrimeNumbers = Table(
      ("non-prime number", "expected factors"),
      (8, List(2, 2, 2)),
      (99, List(3, 3, 11)),
      (100, List(2, 2, 5, 5))
    )

    forAll(nonPrimeNumbers) {
      (number, expectedFactors) => primeFactors(number) should
        equal (expectedFactors)
    }
  }


}
