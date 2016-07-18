package jhunovis.repaymentplan;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static jhunovis.repaymentplan.IsMonthlyRepayment.isRepaymentWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * A unit-test for {@link RepaymentPlan}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentPlanTest {

    @Test
    public void createRepaymentPlan_PaymentLines() throws Exception {
        RepaymentPlan repaymentPlan = CreditParameters.builder()
                .starting(LocalDate.of(2015, Month.NOVEMBER, 30))
                .creditVolume(Money.euro("100000.00"))
                .interestRateInPercent("2.12")
                .repaymentRateInPercent("2.00")
                .durationInYears(10)
                .createCreditParameters()
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

    @Test
    public void createRepaymentPlan_CreditRepaidBeforeDurationEnds_RemainingRatesShouldAmountToZeroEach() throws Exception {
        RepaymentPlan repaymentPlan = CreditParameters.builder()
                .starting(LocalDate.of(2015, Month.DECEMBER, 30))
                .creditVolume(Money.euro("1200.00"))
                .interestRateInPercent("1.00")
                .repaymentRateInPercent("50.00")
                .durationInMonths(25)
                .createCreditParameters()
                .createRepaymentPlan();

        assertThat(
                repaymentPlan.monthlyRepayments(),
                CoreMatchers.hasItems(
                        isRepaymentWith(LocalDate.of(2017, Month.NOVEMBER, 30),
                                Money.euro("39.40"), Money.euro("0.08"), Money.euro("50.92"), Money.euro("51.00")),
                        isRepaymentWith(LocalDate.of(2017, Month.DECEMBER, 31),
                                Money.euro("0.00"), Money.euro("0.03"), Money.euro("39.40"), Money.euro("39.43")),
                        isRepaymentWith(LocalDate.of(2018, Month.JANUARY, 31),
                                Money.euro("0.00"), Money.euro("0.00"), Money.euro("0.00"), Money.euro("0.00"))
                )
        );
    }

    @Test
    public void createRepaymentPlan_Summary() throws Exception {
        RepaymentPlan repaymentPlan = CreditParameters.builder()
                .starting(LocalDate.of(2015, Month.NOVEMBER, 30))
                .creditVolume(Money.euro("100000.00"))
                .interestRateInPercent("2.12")
                .repaymentRateInPercent("2.00")
                .durationInYears(10)
                .createCreditParameters()
                .createRepaymentPlan();

        assertThat(
                repaymentPlan.summary(),
                is(equalTo(
                        new RepaymentSummary(Money.euro("77744.14"), Money.euro("18943.74"), Money.euro("22255.86"), Money.euro("41199.60"))
                ))
        );
    }


}