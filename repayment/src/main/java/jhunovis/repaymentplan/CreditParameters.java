package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * Initial parameters of a credit. Immutable.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class CreditParameters {

    private static final BigDecimal BD_100 = new BigDecimal("100.00");

    @NotNull
    private final LocalDate startingMonth;
    @NotNull
    private final Money creditVolume;
    @NotNull
    private final BigDecimal interestRateInPercent;
    @NotNull
    private final BigDecimal initialRepaymentRateInPercent;
    private final int durationInMonths;

    CreditParameters(@NotNull LocalDate startingMonth, @NotNull Money creditVolume, @NotNull BigDecimal interestRateInPercent,
                     int durationInMonths, @NotNull BigDecimal initialRepaymentRateInPercent) {
        if (creditVolume.isNegative()) {
            throw new IllegalArgumentException("Expecting credit volume as positive amount!");
        }
        assertIsPercentage(interestRateInPercent);
        assertIsPercentage(initialRepaymentRateInPercent);
        if (durationInMonths < 1) {
            throw new IllegalArgumentException("Expecting a runtime of at least a month! Was: " + durationInMonths);
        }

        this.startingMonth = startingMonth.with(TemporalAdjusters.lastDayOfMonth());
        this.creditVolume = creditVolume;
        this.interestRateInPercent = interestRateInPercent;
        this.durationInMonths = durationInMonths;
        this.initialRepaymentRateInPercent = initialRepaymentRateInPercent;
    }

    public Money paymentRate() {
        return creditVolume.monthlyRate(interestRateInPercent.add(initialRepaymentRateInPercent));
    }

    public LocalDate startingMonth() {
        return startingMonth;
    }

    public Money totalDebt() {
        return creditVolume;
    }

    public BigDecimal interestRateInPercent() {
        return interestRateInPercent;
    }

    public BigDecimal initialRepaymentRateInPercent() {
        return initialRepaymentRateInPercent;
    }

    public int durationInMonths() {
        return durationInMonths;
    }

    private void assertIsPercentage(BigDecimal percentage) {
        if (percentage.signum() == -1 || percentage.compareTo(BD_100) >= 1 ) {
            throw new IllegalArgumentException(percentage + " is not a valid percentage!");
        }
    }

    RepaymentPlan createRepaymentPlan() {
        return new RepaymentPlan(this, createMonthlyPayments());
    }

    private List<MonthlyRepayment> createMonthlyPayments() {
        return createMonthlyPaymentPlan(
                new MonthlyRepayment(lastDayOfNexMonth(startingMonth), totalDebt(), interestRateInPercent(), paymentRate())
        );
    }

    private LocalDate lastDayOfNexMonth(LocalDate date) {
        return date.plusMonths(1L).with(TemporalAdjusters.lastDayOfMonth());
    }

    @NotNull
    private List<MonthlyRepayment> createMonthlyPaymentPlan(MonthlyRepayment repayment) {
        List<MonthlyRepayment> monthlyRepayments = new ArrayList<>(durationInMonths());
        for (int runtime = 0; runtime < durationInMonths(); runtime++) {
            monthlyRepayments.add(repayment);
            repayment = repayment.nextMonthlyRepayment();
        }
        return monthlyRepayments;
    }

}
