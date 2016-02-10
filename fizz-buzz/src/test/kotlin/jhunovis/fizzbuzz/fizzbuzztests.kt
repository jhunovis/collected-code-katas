package jhunovis.fizzbuzz

import java.util.stream.IntStream

/**
 * The unit-test for [FizzBuzzKotlin]. It re-uses all test-cases from [FizzBuzzJavaTest].
 *
 * @author [Jan Hackel](mailto:jhunovis@gmail.com)
 */
class FizzBuzzKotlinTest : FizzBuzzJavaTest() {
    override fun fizzBuzz() = FizzBuzzKotlin()
}

/**
 * A unit-test for [FizzBuzzSequenceKotlin]. It re-uses all tests from [FizzBuzzSequenceJavaTest].
 * It uses a different approach for that than [FizzBuzzKotlinTest] because [FizzBuzzSequenceKotlin] does *not*
 * implement interface [FizzBuzzSequence] because both varargs and Java streams do not really fit in well with
 * Kotlin code. In Kotlin the basic collection class can be quickly constructed so there is no need for the varargs,
 * and all streaming code can be directly written on Kotlin collections.
 *
 * That is why this test adapts [FizzBuzzSequenceKotlin] to [FizzBuzzSequence].
 */
class FizzBuzzSequenceKotlinTest : FizzBuzzSequenceJavaTest() {
    override fun fizzBuzzSequence(): FizzBuzzSequence {
        val fizzBuzzSequence = FizzBuzzSequenceKotlin()
        return object : jhunovis.fizzbuzz.FizzBuzzSequence {
            override fun translate(vararg numbers: Int) = fizzBuzzSequence.translate(numbers.asList())
            override fun translate(numbers: IntStream) = translate(*numbers.toArray())
        }
    }
}
