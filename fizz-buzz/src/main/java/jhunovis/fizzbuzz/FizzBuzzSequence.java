package jhunovis.fizzbuzz;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Translate a sequence of natural number to to "Fizz Buzz" numbers.
 * See {@link FizzBuzz} for what a "Fizz Buzz number" is.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public class FizzBuzzSequence {

    private final FizzBuzz fizzBuzz = new FizzBuzz();

    public List<String> translate(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(fizzBuzz::translate)
                .collect(Collectors.toList());
    }
}
