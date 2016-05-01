package jhunovis.repaymentplan;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * A unit-test for {@link RepaymentPlan}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentPlanTest {

    @Test
    public void createRepaymentPlan() throws Exception {
        RepaymentPlan repaymentPlan = RepaymentPlan.builder()
                .starting(LocalDate.of(2016, Month.MAY, 1))
                .creditVolume(Money.euro("100000.00"))
                .interestRateInPercent("2.12")
                .repaymentRateInPercent("2.00")
                .runtimeInYears(10)
                .createRepaymentPlan();

    }
}