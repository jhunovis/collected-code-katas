package jhunovis.fizzbuzz;

/**
 * Translate a natural number into "Fizz Buzz" numbers:
 *
 * Multiples of three translate to “Fizz” instead of the number, multiples of five translate “Buzz”.
 * Numbers which are multiples of both three and five translate to “FizzBuzz”. All other number translate to
 * their natural text representation, e.g. 11 to the text "11".
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public interface FizzBuzz {
    String translate(int number);
}