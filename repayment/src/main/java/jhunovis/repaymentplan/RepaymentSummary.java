package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Used to create a summary over the repayment lines of a {@link RepaymentPlan}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentSummary {

    @NotNull
    private final Money remainingDebt;
    @NotNull
    private final Money totalInterest;
    @NotNull
    private final Money totalRepayments;
    @NotNull
    private final Money totalRates;

    RepaymentSummary(@NotNull Money remainingDebt, @NotNull Money totalInterest, @NotNull Money totalRepayments, @NotNull Money totalRates) {
        this.remainingDebt = remainingDebt;
        this.totalInterest = totalInterest;
        this.totalRepayments = totalRepayments;
        this.totalRates = totalRates;
    }

    @NotNull
    public Money remainingDebt() {
        return remainingDebt;
    }

    @NotNull
    public Money totalInterest() {
        return totalInterest;
    }

    @NotNull
    public Money totalRepayments() {
        return totalRepayments;
    }

    @NotNull
    public Money totalRates() {
        return totalRates;
    }

    /**
     * For acting as mathematical null object in additions.
     */
    static RepaymentSummary zero(Currency currency) {
        return new RepaymentSummary(
                new Money(BigDecimal.ZERO, currency),
                new Money(BigDecimal.ZERO, currency),
                new Money(BigDecimal.ZERO, currency),
                new Money(BigDecimal.ZERO, currency)
        );
    }

    public @NotNull RepaymentSummary add(MonthlyRepayment other) {
        return new RepaymentSummary(
                remainingDebt,
                totalInterest.plus(other.interest()),
                totalRepayments.plus(other.repayment()),
                totalRates.plus(other.monthlyRate())
        );
    }

    public @NotNull RepaymentSummary add(RepaymentSummary other) {
        return new RepaymentSummary(
                remainingDebt,
                totalInterest.plus(other.totalInterest),
                totalRepayments.plus(other.totalRepayments),
                totalRates.plus(other.totalRates)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepaymentSummary that = (RepaymentSummary) o;

        if (!remainingDebt.equals(that.remainingDebt)) return false;
        if (!totalInterest.equals(that.totalInterest)) return false;
        if (!totalRepayments.equals(that.totalRepayments)) return false;
        return totalRates.equals(that.totalRates);

    }

    @Override
    public int hashCode() {
        int result = remainingDebt.hashCode();
        result = 31 * result + totalInterest.hashCode();
        result = 31 * result + totalRepayments.hashCode();
        result = 31 * result + totalRates.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RepaymentSummary{" +
                "remainingDebt=" + remainingDebt +
                ", totalInterest=" + totalInterest +
                ", totalRepayments=" + totalRepayments +
                ", totalRates=" + totalRates +
                '}';
    }
}
