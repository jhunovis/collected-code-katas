package jhunovis.repaymentplan;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Creates {@link CreditParameters} instances. This is the main entry into the creation of repayment plans:
 * Build a {@link CreditParameters} instance using this builder, then class {@link CreditParameters#createRepaymentPlan()}
 * on those credit parameters to compute the {@link RepaymentPlan}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class CreditParameterBuilder {

    private static final BigDecimal BD_100 = new BigDecimal("100.00");

    private LocalDate startingMonth;
    private Money creditVolume;
    private BigDecimal interestRateInPercent;
    private BigDecimal repaymentRateInPercent;
    private int durationInMonths;

    public CreditParameterBuilder starting(LocalDate startingMonth) {
        this.startingMonth = startingMonth;
        return this;
    }

    public CreditParameterBuilder creditVolume(Money creditVolume) {
        if (creditVolume.isNegative()) {
            throw new IllegalArgumentException("Expecting credit volume as positive amount!");
        }
        this.creditVolume = creditVolume;
        return this;
    }

    public CreditParameterBuilder interestRateInPercent(BigDecimal interestRateInPercent) {
        assertIsPercentage(interestRateInPercent);
        this.interestRateInPercent = interestRateInPercent;
        return this;
    }

    public CreditParameterBuilder interestRateInPercent(String interestRateInPercent) {
        return interestRateInPercent(new BigDecimal(interestRateInPercent));
    }

    public CreditParameterBuilder repaymentRateInPercent(BigDecimal repaymentRateInPercent) {
        assertIsPercentage(repaymentRateInPercent);
        this.repaymentRateInPercent = repaymentRateInPercent;
        return this;
    }

    public CreditParameterBuilder repaymentRateInPercent(String repaymentRateInPercent) {
        return repaymentRateInPercent(new BigDecimal(repaymentRateInPercent));
    }

    public CreditParameterBuilder durationInMonths(int durationInMonths) {
        if (durationInMonths < 1) {
            throw new IllegalArgumentException("Expecting a runtime of at least a month! Was: " + durationInMonths);
        }
        this.durationInMonths = durationInMonths;
        return this;
    }

    public CreditParameterBuilder durationInYears(int durationInYears) {
        this.durationInMonths = durationInYears * 12;
        return this;
    }

    /**
     * Build an instance of {@link CreditParameters} using the configured parameters of this instance.
     *
     * @return an instance of {@link CreditParameters}
     */
    public CreditParameters createCreditParameters() {
        return new CreditParameters(startingMonth, creditVolume, interestRateInPercent,
                durationInMonths, repaymentRateInPercent);
    }

    private void assertIsPercentage(BigDecimal percentage) {
        if (percentage.signum() == -1 || percentage.compareTo(BD_100) >= 1 ) {
            throw new IllegalArgumentException(percentage + " is not a valid percentage!");
        }
    }

}
