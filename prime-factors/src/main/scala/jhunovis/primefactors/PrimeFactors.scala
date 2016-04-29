package jhunovis.primefactors

/**
  * The "prime factors" coding kata.
  *
  * The exercise requires the programmer to factorize any integer number into its unique sequence of prime factors,
  * i.e. a list of prime numbers which multiplied with each other equal the number to factorize.
  * Examples: 8 == 2 * 2 * 2; 100 = 2 * 2 * 5 * 5; 37 = 37
  *
  * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
  * @version $Revision$ $Date$ $Author$
  */
object PrimeFactors {
  def primeFactors(number: Int): List[Int] = {
    val factors = factorize(number, 2)
    if (factors.isEmpty) List(number)
    else factors
  }

  private def factorize(number: Int, factor: Int): List[Int] = {
    if (Math.abs(number) <= factor) List(number)
    else if (number % factor == 0) {
      factor :: factorize(number / factor, factor)
    } else {
      factorize(number, factor + 1)
    }
  }

}
