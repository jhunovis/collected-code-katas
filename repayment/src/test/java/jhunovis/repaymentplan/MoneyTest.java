package jhunovis.repaymentplan;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static jhunovis.repaymentplan.TestUtils.percent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * A unit-test for {@link Money}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
@RunWith(JUnitParamsRunner.class)
public final class MoneyTest {

    @Test
    public void subtract() {
        assertThat(
                Money.euro("10.00").subtract(Money.euro("2.22")),
                is(equalTo(Money.euro("7.78")))
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void subtract_GivenDifferentCurrencies_ShouldThrowException() {
        Money.euro("10.00").subtract(Money.forAmountAndCurrency("1.00", "USD"));
    }

    @Test
    public void plus() {
        assertThat(
                Money.euro("14.33").plus(Money.euro("15.22")),
                is(equalTo(Money.euro("29.55")))
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void plus_GivenDifferentCurrencies_ShouldThrowException() {
        Money.euro("10.00").plus(Money.forAmountAndCurrency("1.00", "USD"));
    }

    @Test
    public void negate() {
        assertThat(
                Money.euro("111.33").negate(),
                is(equalTo(Money.euro("-111.33")))
        );
    }

    @Test
    @Parameters({
            "30.00, 10.00, 0.25",
            "77949.76, 2.12, 137.71"
    })
    public void monthlyRate(String amount, String interestRate, String expectedRate) {
        assertThat(
                Money.euro(amount).monthlyRate(percent(interestRate)),
                is(equalTo(Money.euro(expectedRate)))
        );
    }

    @Test
    @Parameters({
            "10.0, 20.0, -1",
            "5.00, 5.00, 0",
            "11.12, 11.11, 1"
    })
    public void compareAmounts(String firstAmount, String secondAmount, int comparisonResult) {
        assertThat(
                Money.euro(firstAmount).compareTo(Money.euro(secondAmount)),
                is(comparisonResult)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareAmount_ShouldThrowExceptionWhenComparingDifferentCurrencies() {
        Money.euro("10.0").compareTo(Money.forAmountAndCurrency("10.00", "USD"));
    }

    @Test
    @Parameters({
            "5.00, 6.00, 5.00",
            "2.00, 1.00, 1.00",
            "3.33, 3.33, 3.33"
    })
    public void min(String firstAmount, String secondAmount, String expectedMinimum) {
        assertThat(
                Money.min(Money.euro(firstAmount), Money.euro(secondAmount)),
                is(equalTo(Money.euro(expectedMinimum)))
        );
    }

    @Test
    @Parameters({
            "5.00, 6.00, 6.00",
            "2.00, 1.00, 2.00",
            "3.33, 3.33, 3.33"
    })
    public void max(String firstAmount, String secondAmount, String expectedMaximum) {
        assertThat(
                Money.max(Money.euro(firstAmount), Money.euro(secondAmount)),
                is(equalTo(Money.euro(expectedMaximum)))
        );
    }
}
