package jhunovis.fizzbuzz;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Translate a sequence of natural number to to "Fizz Buzz" numbers.
 * See {@link FizzBuzz} for what a "Fizz Buzz number" is.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzSequence {

    private final FizzBuzz fizzBuzz = new FizzBuzz();

    public List<String> translate(int... numbers) {
        return translate(Arrays.stream(numbers));
    }

    public List<String> translate(IntStream numbers) {
        return numbers.mapToObj(fizzBuzz::translate).collect(Collectors.toList());
    }
}
