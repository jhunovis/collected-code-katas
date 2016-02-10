package jhunovis.fizzbuzz;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The Java implementation of {@link FizzBuzzSequence}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzSequenceJava implements FizzBuzzSequence {

    private final FizzBuzz fizzBuzz = new FizzBuzzJava();

    @NotNull
    @Override
    public List<String> translate(int... numbers) {
        return translate(Arrays.stream(numbers));
    }

    @NotNull
    public List<String> translate(@NotNull IntStream numbers) {
        return numbers.mapToObj(fizzBuzz::translate).collect(Collectors.toList());
    }
}
