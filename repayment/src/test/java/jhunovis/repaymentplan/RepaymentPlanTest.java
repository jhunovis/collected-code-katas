package jhunovis.repaymentplan;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static jhunovis.repaymentplan.IsMonthlyRepayment.isRepaymentWith;
import static org.junit.Assert.assertThat;

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
                .starting(LocalDate.of(2015, Month.NOVEMBER, 30))
                .creditVolume(Money.euro("100000.00"))
                .interestRateInPercent("2.12")
                .repaymentRateInPercent("2.00")
                .runtimeInYears(10)
                .createRepaymentPlan();

        assertThat(
                repaymentPlan.monthlyRepayments(),
                CoreMatchers.hasItems(
                        isRepaymentWith(LocalDate.of(2015, Month.DECEMBER, 31),
                                Money.euro("99833.34"), Money.euro("176.67"), Money.euro("166.66"), Money.euro("343.33")),
                        isRepaymentWith(LocalDate.of(2016, Month.JANUARY, 31),
                                Money.euro("99666.38"), Money.euro("176.37"), Money.euro("166.96"), Money.euro("343.33")),
                        isRepaymentWith(LocalDate.of(2025, Month.OCTOBER, 31),
                                Money.euro("77949.76"), Money.euro("138.07"), Money.euro("205.26"), Money.euro("343.33")),
                        isRepaymentWith(LocalDate.of(2025, Month.NOVEMBER, 30),
                                Money.euro("77744.14"), Money.euro("137.71"), Money.euro("205.62"), Money.euro("343.33"))
                )
        );
    }
}