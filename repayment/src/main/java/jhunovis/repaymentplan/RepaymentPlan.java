package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    /**
     * @return a builder for repayment plans
     */
    public static Builder builder() {
        return new Builder();
    }

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

    /**
     * Creates {@link RepaymentPlan} instances.
     *
     * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
     * @version $Revision$ $Date$ $Author$
     */
    public static final class Builder {

        private static final BigDecimal BD_100 = new BigDecimal("100.00");

        private LocalDate startingMonth;
        private Money creditVolume;
        private BigDecimal interestRateInPercent;
        private BigDecimal repaymentRateInPercent;
        private int durationInMonths;

        public Builder starting(LocalDate startingMonth) {
            this.startingMonth = startingMonth;
            return this;
        }

        public Builder creditVolume(Money creditVolume) {
            if (creditVolume.isNegative()) {
                throw new IllegalArgumentException("Expecting credit volume as positive amount!");
            }
            this.creditVolume = creditVolume;
            return this;
        }

        public Builder interestRateInPercent(BigDecimal interestRateInPercent) {
            assertIsPercentage(interestRateInPercent);
            this.interestRateInPercent = interestRateInPercent;
            return this;
        }

        public Builder interestRateInPercent(String interestRateInPercent) {
            return interestRateInPercent(new BigDecimal(interestRateInPercent));
        }

        public Builder repaymentRateInPercent(BigDecimal repaymentRateInPercent) {
            assertIsPercentage(repaymentRateInPercent);
            this.repaymentRateInPercent = repaymentRateInPercent;
            return this;
        }

        public Builder repaymentRateInPercent(String repaymentRateInPercent) {
            return repaymentRateInPercent(new BigDecimal(repaymentRateInPercent));
        }

        public Builder durationInMonths(int durationInMonths) {
            if (durationInMonths < 1) {
                throw new IllegalArgumentException("Expecting a runtime of at least a month! Was: " + durationInMonths);
            }
            this.durationInMonths = durationInMonths;
            return this;
        }

        public Builder durationInYears(int durationInYears) {
            this.durationInMonths = durationInYears * 12;
            return this;
        }

        public RepaymentPlan createRepaymentPlan() {
            CreditParameters creditParameters = new CreditParameters(startingMonth, creditVolume, interestRateInPercent,
                    durationInMonths, repaymentRateInPercent);
            return creditParameters.createRepaymentPlan();
        }

        private void assertIsPercentage(BigDecimal percentage) {
            if (percentage.signum() == -1 || percentage.compareTo(BD_100) >= 1 ) {
                throw new IllegalArgumentException(percentage + " is not a valid percentage!");
            }
        }

    }
}
