package jhunovis.fizzbuzz;

import org.jetbrains.annotations.NotNull;

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
public interface FizzBuzzKata {
    /**
     * @return the first one hundred "Fizz Buzz numbers" as a comma separated list.
     */
    @NotNull String firstHundredFizzBuzzNumbers();
}
