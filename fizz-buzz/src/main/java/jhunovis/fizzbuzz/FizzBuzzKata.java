package jhunovis.fizzbuzz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The "Fizz Buzz Kata" main class. The challenge goes like this:
 *
 * Write a program that prints the numbers from 1 to 100. But for multiples of three print “Fizz” instead of the number
 * and for the multiples of five print “Buzz”. For numbers which are multiples of both three and five print “FizzBuzz”.
 * (Quoted from <a href="http://www.c2.com/cgi/wiki?FizzBuzzTest">Cunningham & Cunningham, Inc.: Fizz Buzz Test</a>)
 *
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public class FizzBuzzKata {

    public String firstHundredFizzBuzzNumbers() {
        List<String> translatedNumbers = new FizzBuzzSequence().translate(IntStream.rangeClosed(1, 100));
        return translatedNumbers.stream().collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        System.out.println(new FizzBuzzKata().firstHundredFizzBuzzNumbers());
    }
}
