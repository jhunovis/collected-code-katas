package jhunovis.fizzbuzz;

/**
 * Translate a natural number into "Fizz Buzz" numbers:
 *
 * Multiples of three translate to “Fizz” instead of the number, multiples of five translate “Buzz”.
 * Numbers which are multiples of both three and five translate to “FizzBuzz”. All other number translate to
 * their natural text representation, e.g. 11 to the text "11".
 *
 * @author <a href="mailto:jan.hackel@micros.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzz {
    public String translate(int number) {
        if (isDivisibleByThree(number) && isDivisibleByFive(number)) {
            return "FizzBuzz";
        }if (isDivisibleByThree(number)) {
            return "Fizz";
        } if (isDivisibleByFive(number)) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }

    private boolean isDivisibleByThree(int number) {
        return isDivisible(number, 3);
    }

    private boolean isDivisibleByFive(int number) {
        return isDivisible(number, 5);
    }

    private boolean isDivisible(int dividend, int divisor) {
        return dividend % divisor == 0;
    }
}
