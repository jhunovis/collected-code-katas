package jhunovis.fizzbuzz;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Translate a sequence of natural number to to "Fizz Buzz" numbers.
 * See {@link FizzBuzzJava} for what a "Fizz Buzz number" is.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
interface FizzBuzzSequence {
    @NotNull List<String> translate(int... numbers);
    @NotNull List<String> translate(@NotNull IntStream numbers);
}
