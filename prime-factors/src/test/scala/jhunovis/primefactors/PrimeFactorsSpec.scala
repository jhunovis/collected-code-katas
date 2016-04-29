package jhunovis.primefactors

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}
/**
  * A unit-test for [[PrimeFactors]]. It is very ugly, since it combines many assertions be test case. This makes
  * reading the results difficult, especially if one assertions fails. A [[org.scalatest.PropSpec]] based test would
  * be more suitable, but for now I cannot get them to execute for some weird dependency issues.
  *
  * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
  * @version $Revision$ $Date$ $Author$
  */
@RunWith(classOf[JUnitRunner])
class PrimeFactorsSpec extends FlatSpec with Matchers {

  "Prime numbers" should "factorize to themselves." in {
    PrimeFactors.primeFactors(1) should equal (List(1))
    PrimeFactors.primeFactors(2) should equal (List(2))
    PrimeFactors.primeFactors(3) should equal (List(3))
    PrimeFactors.primeFactors(5) should equal (List(5))
    PrimeFactors.primeFactors(37) should equal (List(37))
  }

  "Non-prime number" should "factorize into their prime factors" in {
    PrimeFactors.primeFactors(16) should equal (List(2, 2, 2, 2))
    PrimeFactors.primeFactors(99) should equal (List(3, 3, 11))
    PrimeFactors.primeFactors(100) should equal (List(2, 2, 5, 5))
  }

  "Negative numbers" should "be factorizable too." in {
    PrimeFactors.primeFactors(-1) should equal (List(-1))
    PrimeFactors.primeFactors(-2) should equal (List(-2))
    PrimeFactors.primeFactors(-3) should equal (List(-3))
    PrimeFactors.primeFactors(-48) should equal (List(2, 2, 2, 2, -3))
    PrimeFactors.primeFactors(-16) should equal (List(2, 2, 2, -2))
  }

}
