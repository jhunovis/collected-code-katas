package jhunovis.fizzbuzz

/**
 * A Kotlin implementation of [FizzBuzz]. Normally a package level function would do very well for this task, but this
 * would make it difficult to re-use the Java unit-tests.
 *
 * @author [Jan Hackel](mailto:jhunovisl@gmail.com)
 */
class FizzBuzzKotlin : FizzBuzz {

    override fun translate(number: Int): String {
        return when {
            number.isDivisibleBy(3) && number.isDivisibleBy(5) -> "FizzBuzz"
            number.isDivisibleBy(3) -> "Fizz"
            number.isDivisibleBy(5) -> "Buzz"
            else -> number.toString()
        }
    }

}

fun Int.isDivisibleBy(divisor : Int) = this % divisor == 0

/**
 * Translate a sequence of natural number to to "Fizz Buzz" numbers.
 * See [FizzBuzz] for what a "Fizz Buzz number" is.
 *
 * @author [Jan Hackel](mailto:jhunovisl@gmail.com)
 */
class FizzBuzzSequenceKotlin {

    private val fizzBuzz: FizzBuzz = FizzBuzzKotlin()

    fun translate(numbers: List<Int>) = numbers.map { fizzBuzz.translate(it) }

}

/**
 * Main entry to produce the first one hundred "fizz buzz numbers".
 */
class FizzBuzzKataKotlin : FizzBuzzKata {
    override fun firstHundredFizzBuzzNumbers() =
            FizzBuzzSequenceKotlin()
                    .translate((1..100).toList())
                    .joinToString()
}