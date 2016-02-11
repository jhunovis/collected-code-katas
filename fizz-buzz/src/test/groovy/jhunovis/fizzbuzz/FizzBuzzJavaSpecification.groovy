package jhunovis.fizzbuzz

import spock.lang.Specification

/**
 * A Spock specification for {@link FizzBuzzJava}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzJavaSpecification extends Specification {

    private fizzBuzz = new FizzBuzzJava()

    def "Fizz numbers"() {
        expect:
        fizzBuzz.translate(number) == "Fizz"

        where:
        number <<  [3, 6, 9, 12, 18, 93]
    }

    def "Buzz numbers"() {
        expect:
        fizzBuzz.translate(number) == "Buzz"

        where:
        number <<  [5, 10, 20, 25, 55, 95]
    }

    def "FizzBuzz numbers"() {
        expect:
        fizzBuzz.translate(number) == "FizzBuzz"

        where:
        number <<  [0, 15, 30, 45, 60, 75, 90]
    }

    def "untranslated numbers"() {
        expect:
        fizzBuzz.translate(number) == number.toString()

        where:
        number <<  [1, 2, 4, 11, 13, 16, 91]
    }

}
