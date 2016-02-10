package jhunovis.fizzbuzz

/**
 * A Kotlin implementation of [FizzBuzz]. Normally a package level function would do very well for this task, but this
 * would make it difficult to re-use the Java unit-tests.
 *
 * @author <a href="mailto:jhunovisl@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzKotlin : FizzBuzz {

    override fun translate(number: Int): String {
        return when {
            isDivisibleByThree(number) && isDivisibleByFive(number) -> "FizzBuzz"
            isDivisibleByThree(number) -> "Fizz"
            isDivisibleByFive(number) -> "Buzz"
            else -> number.toString()
        }
    }

    private fun isDivisibleByFive(number: Int) = number % 5 == 0

    private fun isDivisibleByThree(number: Int) = number % 3 == 0

}

/**
 * Translate a sequence of natural number to to "Fizz Buzz" numbers.
 * See [FizzBuzz] for what a "Fizz Buzz number" is.
 *
 * @author <a href="mailto:jhunovisl@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzSequenceKotlin {

    private val fizzBuzz: FizzBuzz = FizzBuzzKotlin()

    fun translate(numbers: List<Int>) : List<String> = numbers.map { fizzBuzz.translate(it) }

}

