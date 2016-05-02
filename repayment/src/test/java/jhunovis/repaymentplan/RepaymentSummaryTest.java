package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * A unit-test for {@link RepaymentSummary}.
 * 
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentSummaryTest {

    @Test
    public void addMonthlyRepayment() throws Exception {
        assertThat(
                repaymentAccumulator("7.00", "10.00", "20.0", "30.0").add(monthlyRepayment("100.00", "10.0", "35.0")),
                is(equalTo(
                        repaymentAccumulator("7.00", "10.83", "54.17", "65.00")
                ))
        );
    }

    @Test
    public void addRepaymentAccumulator() throws Exception {
        assertThat(
                repaymentAccumulator("5.00", "10.00", "20.0", "30.0").add(repaymentAccumulator("6.00", "15.00", "25.0", "35.0")),
                is(equalTo(
                        repaymentAccumulator("5.00", "25.00", "45.00", "65.00")
                ))
        );
    }

    @NotNull
    private MonthlyRepayment monthlyRepayment(String debt, String interestRate, String monthlyRate) {
        return new MonthlyRepayment(TestUtils.anyDate(), Money.euro(debt), TestUtils.percent(interestRate), Money.euro(monthlyRate));
    }

    private RepaymentSummary repaymentAccumulator(String remainingDebt, String interest, String repayment, String rate) {
        return new RepaymentSummary(Money.euro(remainingDebt), Money.euro(interest), Money.euro(repayment), Money.euro(rate));
    }


}