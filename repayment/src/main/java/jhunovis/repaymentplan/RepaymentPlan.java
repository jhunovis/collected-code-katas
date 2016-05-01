package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentPlan {

    @NotNull
    private final List<MonthlyRepayment> monthlyRepayments;

    /**
     * @return a builder for repayment plans
     */
    public static Builder builder() {
        return new Builder();
    }

    RepaymentPlan(@NotNull CreditParameters creditParameters, @NotNull List<MonthlyRepayment> monthlyRepayments) {
        this.monthlyRepayments = monthlyRepayments;
    }

    @NotNull
    public List<MonthlyRepayment> monthlyRepayments() {
        return Collections.unmodifiableList(monthlyRepayments);
    }


    /**
     * Creates {@link RepaymentPlan} instances.
     *
     * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
     * @version $Revision$ $Date$ $Author$
     */
    public static final class Builder {


        private LocalDate startingMonth;
        private Money creditVolume;
        private BigDecimal interestRateInPercent;
        private BigDecimal repaymentRateInPercent;
        private int runtimeInMonths;

        public Builder starting(LocalDate startingMonth) {
            this.startingMonth = startingMonth;
            return this;
        }

        public Builder creditVolume(Money creditVolume) {
            this.creditVolume = creditVolume;
            return this;
        }

        public Builder interestRateInPercent(BigDecimal interestRateInPercent) {
            this.interestRateInPercent = interestRateInPercent;
            return this;
        }

        public Builder interestRateInPercent(String interestRateInPercent) {
            return interestRateInPercent(new BigDecimal(interestRateInPercent));
        }

        public Builder repaymentRateInPercent(BigDecimal repaymentRateInPercent) {
            this.repaymentRateInPercent = repaymentRateInPercent;
            return this;
        }

        public Builder repaymentRateInPercent(String repaymentRateInPercent) {
            return repaymentRateInPercent(new BigDecimal(repaymentRateInPercent));
        }

        public Builder runtimeInMonths(int runtimeInMonths) {
            this.runtimeInMonths = runtimeInMonths;
            return this;
        }

        public Builder runtimeInYears(int runtimeInYears) {
            this.runtimeInMonths = runtimeInYears * 12;
            return this;
        }

        public RepaymentPlan createRepaymentPlan() {
            CreditParameters creditParameters = new CreditParameters(startingMonth, creditVolume, interestRateInPercent,
                    runtimeInMonths, repaymentRateInPercent);
            return creditParameters.createRepaymentPlan();
        }



    }
}
