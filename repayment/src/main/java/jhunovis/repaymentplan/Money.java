package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

/**
 * Represents monetary amounts. Immutable.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class Money {

    @NotNull
    private final BigDecimal amount;
    @NotNull
    private final Currency currency;
    private static final BigDecimal BD_1200 = new BigDecimal("1200.00");

    public Money(@NotNull BigDecimal amount, @NotNull Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Static factory method producing a money object representing an amount of zero in the local currency.
     */
    public static Money zeroForLocalCurrency() {
        return new Money(BigDecimal.ZERO, Currency.getInstance(Locale.getDefault()));
    }

    /**
     * Static factory method for monetary amounts
     *
     * @param amount the amount as string. Must be suitable for constructing {@link BigDecimal} instances.
     * @param currencyCode a currency code suitable as produced by {@link Currency#getCurrencyCode()}.
     * @return an instance of this class representing the monetary amount
     */
    public static Money forAmountAndCurrency(@NotNull String amount, @NotNull String currencyCode) {
        Currency currency = Currency.getInstance(currencyCode);
        if (currency != null) {
            return new Money(new BigDecimal(amount), currency);
        } else {
            throw new IllegalArgumentException("Unknown currency code "  + currencyCode);
        }
    }

    /**
     * Static factory method for Euro amounts.
     *
     * @param amount the amount in a format suitable for {@link BigDecimal}.
     * @return an Euro amount
     */
    public static Money euro(@NotNull String amount) {
        return forAmountAndCurrency(amount, "EUR");
    }

    /**
     * @return the currency for this money object
     */
    public Currency currency() {
        return currency;
    }

    /**
     * @return {@code true}, iff the amount of this money instance is negative
     */
    public boolean isNegative() {
        return amount.signum() == -1;
    }

    /**
     * Subtract the given amount from the amount of this instance and return the result.
     *
     * @param other a monetary amount. Must have the same currency as this.
     * @return {@code this - other}
     */
    @NotNull
    public Money subtract(@NotNull Money other) {
        assertSameCurrency(other);
        return new Money(amount.subtract(other.amount), currency);
    }

    @NotNull
    public Money plus(@NotNull Money other) {
        assertSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }

    /**
     * Compute the monthly rate for a given interest rate of this amount.
     *
     * @param yearlyRateInPercent the interest rate
     * @return the monthly rate of this amount for the given yearly interest
     */
    @NotNull
    public Money monthlyRate(@NotNull BigDecimal yearlyRateInPercent) {
        return new Money(percentage(yearlyRateInPercent), currency);
    }

    private BigDecimal percentage(BigDecimal percentage) {
        return amount.multiply(percentage).divide(BD_1200, currency.getDefaultFractionDigits(), BigDecimal.ROUND_HALF_UP);
    }

    private void assertSameCurrency(Money other) {
        if (! currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot operate on money with different currencies (" +
                    currency.getCurrencyCode() + " vs " + other.currency + ")");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (amount.compareTo(money.amount) != 0) return false;
        return currency.equals(money.currency);

    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return amount + " " + currency.getCurrencyCode();
    }

}
