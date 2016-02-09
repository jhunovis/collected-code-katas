package jhunovis.fizzbuzz;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * A unit-test for {@link FizzBuzzSequence}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public class FizzBuzzSequenceTest {
    @Test
    public void testTranslate() throws Exception {
        List<String> translatedNumbers = new FizzBuzzSequence().translate(1, 2, 3, 4, 5, 6, 10, 12, 15, 16, 20, 45, 89, 90);

        assertThat(
                translatedNumbers,
                IsIterableContainingInOrder.contains(
                        "1", "2", "Fizz", "4", "Buzz", "Fizz", "Buzz", "Fizz", "FizzBuzz", "16", "Buzz", "FizzBuzz", "89", "FizzBuzz"
                )
        );
    }

}