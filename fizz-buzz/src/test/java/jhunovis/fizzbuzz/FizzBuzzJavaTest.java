package jhunovis.fizzbuzz;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * A JUnit4 unit-test for {@link FizzBuzzJava}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
@RunWith(JUnitParamsRunner.class)
public class FizzBuzzJavaTest {

    @NotNull
    FizzBuzz fizzBuzz() {
        return new FizzBuzzJava();
    }

    @Test
    @Parameters({"3", "9", "12", "21"})
    public void testFizz(int number) throws Exception {
        String translatedNumber = fizzBuzz().translate(number);

        assertThat(
                translatedNumber, is(equalTo("Fizz"))
        );
    }

    @Test
    @Parameters({"5", "10", "20", "50"})
    public void testBuzz(int number) throws Exception {
        String translatedNumber = fizzBuzz().translate(number);

        assertThat(
                translatedNumber, is(equalTo("Buzz"))
        );
    }

    @Test
    @Parameters({"15", "30", "45", "90"})
    public void testFizzBuzz(int number) throws Exception {
        String translatedNumber = fizzBuzz().translate(number);

        assertThat(
                translatedNumber, is(equalTo("FizzBuzz"))
        );
    }

    @Test
    @Parameters({"1", "2", "4", "7", "14", "32"})
    public void testNeitherFizzNorBuzzNorFizzBuzz(int number) throws Exception {
        String translatedNumber = fizzBuzz().translate(number);

        assertThat(
                translatedNumber, is(equalTo(String.valueOf(number)))
        );
    }
}