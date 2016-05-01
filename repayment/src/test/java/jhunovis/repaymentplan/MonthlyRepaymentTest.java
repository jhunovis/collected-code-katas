package jhunovis.repaymentplan;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static jhunovis.repaymentplan.TestUtils.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * A unit-test for {@link MonthlyRepayment}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class MonthlyRepaymentTest {

    @Test
    public void dueDate_ShouldBeFixed() throws Exception {
        MonthlyRepayment repayment = new MonthlyRepayment(LocalDate.of(2016, Month.MAY, 1), anyMoney(), anyRate(), anyMoney());

        assertThat(
                repayment.dueDate(),
                is(equalTo(LocalDate.of(2016, Month.MAY, 1)))
        );
    }

    @Test
    public void remainingDept_ShouldBeComputedFromInitialDebtAndRepayment() throws Exception {
        MonthlyRepayment repayment = new MonthlyRepayment(anyDate(), Money.euro("1000.00"), interestRate("2.00"), Money.euro("20.00"));

        assertThat(
                repayment.remainingDept(),
                is(equalTo(Money.euro("981.67")))
        );
    }

    @Test
    public void interest_ShouldBeComputedFromDeptAndInterestRate() throws Exception {
        MonthlyRepayment repayment = new MonthlyRepayment(anyDate(), Money.euro("200.00"), interestRate("3.00"), anyMoney());

        assertThat(
                repayment.interest(),
                is(equalTo(Money.euro("0.50")))
        );
    }

    @Test
    public void repayment_ShouldBeComputedFromMonthlyRateAndInterest() throws Exception {
        MonthlyRepayment repayment = new MonthlyRepayment(anyDate(), Money.euro("100.00"), interestRate("2.00"), Money.euro("10.00"));

        assertThat(
                repayment.repayment(),
                is(equalTo(Money.euro("9.83")))
        );
    }

    @Test
    public void monthlyRate_ShouldBeFixed() throws Exception {
        MonthlyRepayment repayment = new MonthlyRepayment(anyDate(), anyMoney(), anyRate(), Money.euro("10.00"));

        assertThat(
                repayment.monthlyRate(),
                is(equalTo(Money.euro("10.00")))
        );
    }

    @Test
    public void nextMonthlyRate() throws Exception {
        MonthlyRepayment monthlyRepayment = new MonthlyRepayment(LocalDate.of(2015, Month.DECEMBER, 31),
                Money.euro("1000.00"), interestRate("7.00"), Money.euro("100.00"));

        assertThat(
                monthlyRepayment.nextMonthlyRepayment(),
                is(equalTo(
                        new MonthlyRepayment(
                                LocalDate.of(2016, Month.JANUARY, 31),
                                Money.euro("905.83"), interestRate("7.00"), Money.euro("100.00")
                        )
                ))
        );
    }

}