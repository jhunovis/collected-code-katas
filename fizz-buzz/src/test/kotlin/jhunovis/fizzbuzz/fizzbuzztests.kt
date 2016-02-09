package jhunovis.fizzbuzz

/**
 * The unit-test for [FizzBuzzKotlin]. It re-uses all test-cases from [FizzBuzzJavaTest].
 *
 * @author [Jan Hackel](mailto:jhunovis@gmail.com)
 */
class FizzBuzzTests : FizzBuzzJavaTest() {

    override fun fizzBuzz(): FizzBuzz {
        return FizzBuzzKotlin();
    }

}