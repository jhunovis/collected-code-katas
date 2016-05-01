package jhunovis.repaymentplan;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static jhunovis.repaymentplan.TestUtils.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * A unit-test for {@link CreditParameters}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
@RunWith(JUnitParamsRunner.class)
public final class CreditParametersTest {
    @Test
    @Parameters({
            "100000.00, 2.12, 2.00, 343.33",
            "10000.00, 2.02, 5.00, 58.50"
    })
    public void paymentRate(String totalCreditAmount, String interestRateInPercent, String repaymentRateInPercent, String expectedMonthlyRepaymentAmount) throws Exception {
        CreditParameters creditParameters = new CreditParameters(anyDate(),
                Money.euro(totalCreditAmount), interestRate(interestRateInPercent), 0, percent(repaymentRateInPercent));

        assertThat(
               creditParameters.paymentRate(),
                is(equalTo(Money.euro(expectedMonthlyRepaymentAmount)))
        );
    }

}