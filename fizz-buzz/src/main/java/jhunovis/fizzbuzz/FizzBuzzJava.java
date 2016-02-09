package jhunovis.fizzbuzz;

/**
 * Java implementation of {@link FizzBuzz}. See there.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class FizzBuzzJava implements FizzBuzz {
    @Override
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
