package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Represents the repayment for a single month. Immutable.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class MonthlyRepayment {

    @NotNull
    private final LocalDate dueDate;
    @NotNull
    private final Money debt;
    @NotNull
    private final BigDecimal interestRate;
    @NotNull
    private final Money monthlyRate;

    public MonthlyRepayment(@NotNull LocalDate dueDate, @NotNull Money debt, @NotNull BigDecimal interestRate, @NotNull Money monthlyRate) {
        this.dueDate = dueDate;
        this.debt = debt;
        this.interestRate = interestRate;
        this.monthlyRate = monthlyRate;
    }

    /**
     * @return when this monthly repayment is due
     */
    public LocalDate dueDate() {
        return dueDate;
    }

    /**
     * @return the debt remaining to settled over the period of the credit
     */
    public Money remainingDept() {
        return debt.subtract(repayment());
    }


    /**
     * @return the interest rate. Fix over runtime of credit.
     */
    public @NotNull BigDecimal interestRate() {
        return interestRate;
    }

    /**
     * @return the interest due in this month
     */
    public Money interest() {
        return debt.monthlyRate(interestRate);
    }

    /**
     * @return the effective amount by which the debt for this month will be reduced by the current repayment
     */
    public Money repayment() {
        return monthlyRate().subtract(interest());
    }

    /**
     * @return the monthly rate for this credit
     */
    public Money monthlyRate() {
        return monthlyRate;
    }

    /**
     * Factory method creating the payment plan for the next month.
     *
     * @return the payment plan for the next month
     */
    public MonthlyRepayment nextMonthlyRepayment() {
        return new MonthlyRepayment(
                lastDayOfNextMonth(), remainingDept(), interestRate, monthlyRate()
        );
    }

    private LocalDate lastDayOfNextMonth() {
        return dueDate.plusMonths(1L).with(TemporalAdjusters.lastDayOfMonth());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthlyRepayment that = (MonthlyRepayment) o;

        if (!dueDate.equals(that.dueDate)) return false;
        if (!debt.equals(that.debt)) return false;
        if (!interestRate.equals(that.interestRate)) return false;
        return monthlyRate.equals(that.monthlyRate);

    }

    @Override
    public int hashCode() {
        int result = dueDate.hashCode();
        result = 31 * result + debt.hashCode();
        result = 31 * result + interestRate.hashCode();
        result = 31 * result + monthlyRate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MonthlyRepayment{" +
                "dueDate=" + dueDate +
                ", debt=" + debt +
                ", interestRate=" + interestRate +
                ", monthlyRate=" + monthlyRate +
                '}';
    }
}
