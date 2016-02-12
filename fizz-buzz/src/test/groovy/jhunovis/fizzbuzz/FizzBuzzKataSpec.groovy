package jhunovis.fizzbuzz

import spock.lang.Specification
import spock.lang.Unroll


/**
 * A Spock specification for {@link FizzBuzzKata}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzKataSpec extends Specification {
    @Unroll
    def "test firstHundredFizzBuzzNumbers"() {
        when:
        def fizzBuzzNumbers = fizzBuzzKata.firstHundredFizzBuzzNumbers()
        then:
        fizzBuzzNumbers.startsWith("1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16")
        fizzBuzzNumbers.contains("FizzBuzz, 46, 47, Fizz, 49, Buzz, Fizz, 52, 53, Fizz, Buzz, 56,")
        fizzBuzzNumbers.endsWith("Fizz, Buzz, 86, Fizz, 88, 89, FizzBuzz, 91, 92, Fizz, 94, Buzz, Fizz, 97, 98, Fizz, Buzz")
        where:
        fizzBuzzKata << [new FizzBuzzKataJava(), new FizzBuzzKataKotlin()]
    }
}
