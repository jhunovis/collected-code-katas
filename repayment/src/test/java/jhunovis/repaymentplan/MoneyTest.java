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
    public void subtract() throws Exception {
        assertThat(
                Money.euro("10.00").subtract(Money.euro("2.22")),
                is(equalTo(Money.euro("7.78")))
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void subtract_GivenDifferentCurrencies_ShouldThrowException() throws Exception {
        Money.euro("10.00").subtract(Money.forAmountAndCurrency("1.00", "USD"));
    }

    @Test
    public void plus() throws Exception {
        assertThat(
                Money.euro("14.33").plus(Money.euro("15.22")),
                is(equalTo(Money.euro("29.55")))
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void plus_GivenDifferentCurrencies_ShouldThrowException() throws Exception {
        Money.euro("10.00").plus(Money.forAmountAndCurrency("1.00", "USD"));
    }

    @Test
    @Parameters({
            "30.00, 10.00, 0.25",
            "77949.76, 2.12, 137.71"
    })
    public void monthlyRate(String amount, String interestRate, String expectedRate) throws Exception {
        assertThat(
                Money.euro(amount).monthlyRate(percent(interestRate)),
                is(equalTo(Money.euro(expectedRate)))
        );
    }

}
