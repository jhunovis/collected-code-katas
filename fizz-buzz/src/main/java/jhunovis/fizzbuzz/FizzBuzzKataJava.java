package jhunovis.fizzbuzz;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Java implementation of the {@link FizzBuzzKata}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzKataJava implements FizzBuzzKata {

    @NotNull
    @Override
    public String firstHundredFizzBuzzNumbers() {
        List<String> translatedNumbers = new FizzBuzzSequenceJava().translate(IntStream.rangeClosed(1, 100));
        return translatedNumbers.stream().collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        System.out.println(new FizzBuzzKataJava().firstHundredFizzBuzzNumbers());
    }
}
