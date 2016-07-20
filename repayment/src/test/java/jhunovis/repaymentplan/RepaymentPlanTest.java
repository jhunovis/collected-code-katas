package jhunovis.repaymentplan;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static jhunovis.repaymentplan.IsMonthlyRepayment.*;
import static jhunovis.repaymentplan.Money.euro;
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

    @SuppressWarnings("unchecked")
    @Test
    public void createRepaymentPlan_PaymentLines() throws Exception {
        RepaymentPlan repaymentPlan = CreditParameters.builder()
                .starting(LocalDate.of(2015, Month.NOVEMBER, 30))
                .creditVolume(euro("100000.00"))
                .interestRateInPercent("2.12")
                .repaymentRateInPercent("2.00")
                .durationInYears(10)
                .createCreditParameters()
                .createRepaymentPlan();

        assertThat(
                repaymentPlan.monthlyRepayments(),
                CoreMatchers.hasItems(
                        isRepaymentWith(dueDate(2015, Month.DECEMBER, 31),
                                remainingDebt(euro("99833.34")), interest(euro("176.67")),
                                repayment(euro("166.66")), monthlyRate(euro("343.33"))),
                        isRepaymentWith(dueDate(2016, Month.JANUARY, 31),
                                remainingDebt(euro("99666.38")), interest(euro("176.37")),
                                repayment(euro("166.96")), monthlyRate(euro("343.33"))),
                        isRepaymentWith(dueDate(2025, Month.OCTOBER, 31),
                                remainingDebt(euro("77949.76")), interest(euro("138.07")),
                                repayment(euro("205.26")), monthlyRate(euro("343.33"))),
                        isRepaymentWith(dueDate(2025, Month.NOVEMBER, 30),
                                remainingDebt(euro("77744.14")), interest(euro("137.71")),
                                repayment(euro("205.62")), monthlyRate(euro("343.33")))
                )
        );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRepaymentPlan_CreditRepaidBeforeDurationEnds_RemainingRatesShouldAmountToZeroEach() throws Exception {
        RepaymentPlan repaymentPlan = CreditParameters.builder()
                .starting(LocalDate.of(2015, Month.DECEMBER, 30))
                .creditVolume(euro("1200.00"))
                .interestRateInPercent("1.00")
                .repaymentRateInPercent("50.00")
                .durationInMonths(25)
                .createCreditParameters()
                .createRepaymentPlan();

        assertThat(
                repaymentPlan.monthlyRepayments(),
                CoreMatchers.hasItems(
                        isRepaymentWith(dueDate(2017, Month.NOVEMBER, 30),
                                remainingDebt(euro("39.40")), interest(euro("0.08")),
                                repayment(euro("50.92")), monthlyRate(euro("51.00"))),
                        isRepaymentWith(dueDate(2017, Month.DECEMBER, 31),
                                remainingDebt(euro("0.00")), interest(euro("0.03")),
                                repayment(euro("39.40")), monthlyRate(euro("39.43"))),
                        isRepaymentWith(dueDate(2018, Month.JANUARY, 31),
                                remainingDebt(euro("0.00")), interest(euro("0.00")),
                                repayment(euro("0.00")), monthlyRate(euro("0.00")))
                )
        );
    }

    @Test
    public void createRepaymentPlan_Summary() throws Exception {
        RepaymentPlan repaymentPlan = CreditParameters.builder()
                .starting(LocalDate.of(2015, Month.NOVEMBER, 30))
                .creditVolume(euro("100000.00"))
                .interestRateInPercent("2.12")
                .repaymentRateInPercent("2.00")
                .durationInYears(10)
                .createCreditParameters()
                .createRepaymentPlan();

        assertThat(
                repaymentPlan.summary(),
                is(equalTo(
                        new RepaymentSummary(euro("77744.14"), euro("18943.74"), euro("22255.86"), euro("41199.60"))
                ))
        );
    }


}