package jhunovis.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

/**
 * A unit-test for {@link FizzBuzzKata}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public class FizzBuzzKataTest {

    @Test
    public void testFizzBuzzKata() throws Exception {
        FizzBuzzKata fizzBuzzKata = new FizzBuzzKata();

        String firstFizzBuzzNumbers = fizzBuzzKata.firstHundredFizzBuzzNumbers();

        assertThat(
                firstFizzBuzzNumbers,
                allOf(
                        startsWith("1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16"),
                        containsString("FizzBuzz, 46, 47, Fizz, 49, Buzz, Fizz, 52, 53, Fizz, Buzz, 56,"),
                        endsWith("Fizz, Buzz, 86, Fizz, 88, 89, FizzBuzz, 91, 92, Fizz, 94, Buzz, Fizz, 97, 98, Fizz, Buzz")
                )
        );

    }
}