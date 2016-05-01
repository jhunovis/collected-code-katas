package jhunovis.repaymentplan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

/**
 * Shared code for unit-tests.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
final class TestUtils {

    public static BigDecimal interestRate(String interestRate) {
        return new BigDecimal(interestRate);
    }

    public static Money anyMoney() {
        return Money.forAmountAndCurrency("0.00", "USD");
    }

    public static BigDecimal anyRate() {
        return BigDecimal.ZERO;
    }

    public static LocalDate anyDate() {
        return LocalDate.of(2000, Month.JANUARY, 1);
    }

    public static BigDecimal percent(String percentage) {
        return new BigDecimal(percentage);
    }
}
