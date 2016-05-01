package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private final int runtimeInMonths;

    CreditParameters(@NotNull LocalDate startingMonth, @NotNull Money creditVolume, @NotNull BigDecimal interestRateInPercent,
                     int runtimeInMonths, @NotNull BigDecimal initialRepaymentRateInPercent) {
        if (creditVolume.isNegative()) {
            throw new IllegalArgumentException("Expecting credit volume as positive amount!");
        }
        assertIsPercentage(interestRateInPercent);
        assertIsPercentage(initialRepaymentRateInPercent);
        if (runtimeInMonths < 1) {
            throw new IllegalArgumentException("Expecting a runtime of at least a month! Was: " + runtimeInMonths);
        }

        this.startingMonth = startingMonth;
        this.creditVolume = creditVolume;
        this.interestRateInPercent = interestRateInPercent;
        this.runtimeInMonths = runtimeInMonths;
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

    public int runtimeInMonth() {
        return runtimeInMonths;
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
        return null;
    }
}
