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
        this.startingMonth = startingMonth.with(TemporalAdjusters.lastDayOfMonth());
        this.creditVolume = creditVolume;
        this.interestRateInPercent = interestRateInPercent;
        this.durationInMonths = durationInMonths;
        this.initialRepaymentRateInPercent = initialRepaymentRateInPercent;
    }

    /**
     * @return a builder for repayment plans
     */
    public static CreditParameterBuilder builder() {
        return new CreditParameterBuilder();
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

    /**
     * Factory method for repayment plans.
     */
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

    @Override
    public String toString() {
        return "CreditParameters{" +
                "startingMonth=" + startingMonth +
                ", creditVolume=" + creditVolume +
                ", interestRateInPercent=" + interestRateInPercent +
                ", initialRepaymentRateInPercent=" + initialRepaymentRateInPercent +
                ", durationInMonths=" + durationInMonths +
                '}';
    }
}
