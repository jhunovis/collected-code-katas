package jhunovis.fizzbuzz

import spock.lang.Specification

import java.util.stream.IntStream

/**
 * A Spock specification for {@link FizzBuzzSequenceJava}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzSequenceJavaSpecification extends Specification {
    def "translate number sequences to Fizz-Buzz numbers. Var-arg version."() {
        expect:
        new FizzBuzzSequenceJava().translate(1, 2, 3, 4, 5, 6, 10, 12, 15, 16, 20, 45, 89, 90) ==
                ["1", "2", "Fizz", "4", "Buzz", "Fizz", "Buzz", "Fizz", "FizzBuzz", "16", "Buzz", "FizzBuzz", "89", "FizzBuzz"];
    }

    def "translate number sequences to Fizz-Buzz numbers. Stream version."() {
        expect:
        new FizzBuzzSequenceJava().translate(IntStream.of(1, 2, 3, 4, 5, 6, 10, 12, 15, 16, 20, 45, 89, 90)) ==
                ["1", "2", "Fizz", "4", "Buzz", "Fizz", "Buzz", "Fizz", "FizzBuzz", "16", "Buzz", "FizzBuzz", "89", "FizzBuzz"];
    }
}
