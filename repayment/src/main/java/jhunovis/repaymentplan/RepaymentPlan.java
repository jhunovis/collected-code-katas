package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * A repayment plan plan for credit with a fixed interest rate over a predefined duration.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentPlan {

    @NotNull
    private final CreditParameters creditParameters;
    @NotNull
    private final List<MonthlyRepayment> monthlyRepayments;

    RepaymentPlan(@NotNull CreditParameters creditParameters, @NotNull List<MonthlyRepayment> monthlyRepayments) {
        this.creditParameters = creditParameters;
        this.monthlyRepayments = monthlyRepayments;
    }

    /**
     * Get the fixed credit parameters for this repayment plan.
     */
    public CreditParameters creditParameters() {
        return creditParameters;
    }

    /**
     * Get the individual payments due for each month of this payment plan.
     */
    @NotNull
    public List<MonthlyRepayment> monthlyRepayments() {
        return Collections.unmodifiableList(monthlyRepayments);
    }

    /**
     * Get summary on the remaining debt, total interest payed, total repayments payed , and total rates payed
     * at the end of this payment plan.
     */
    public @NotNull RepaymentSummary summary() {
        RepaymentSummary totals = monthlyRepayments.stream()
                .reduce(zero(), RepaymentSummary::add, RepaymentSummary::add);
        return new RepaymentSummary(remainingDebt(), totals.totalInterest(), totals.totalRepayments(), totals.totalRates());
    }

    private Money remainingDebt() {
        if (monthlyRepayments.isEmpty()) {
            return Money.zeroForLocalCurrency();
        } else {
            return monthlyRepayments.get(monthlyRepayments.size() - 1).remainingDept();
        }
    }


    private RepaymentSummary zero() {
        return RepaymentSummary.zero(currencyForThisPlan());
    }

    private Currency currencyForThisPlan() {
        if (monthlyRepayments.isEmpty()) {
            return Currency.getInstance(Locale.getDefault());
        } else {
            return monthlyRepayments.get(0).monthlyRate().currency();
        }
    }

}
