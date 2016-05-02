package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Creates a text representation for {@link RepaymentPlan} instances.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentPlanPrinter {

    @NotNull
    private final PrintWriter printWriter;
    @NotNull
    private final Locale locale;

    public RepaymentPlanPrinter(@NotNull PrintWriter printWriter, @NotNull Locale locale) {
        this.printWriter = printWriter;
        this.locale = locale;
    }

    /**
     * Print a text representation of the given repayment plan into the configured print writer.
     *
     * @param repaymentPlan the repayment plan to print
     */
    public void print(@NotNull RepaymentPlan repaymentPlan) {
        printHeader();
        printPaymentRow(repaymentPlan.creditParameters());
        printRepaymentRows(repaymentPlan.monthlyRepayments());
        printSummary(repaymentPlan.summary());
    }

    private void printHeader() {
        printRow("Datum", "Restschuld", "Zinsen", "Tilgung(+)/Auszahlung(-)", "Rate");
    }

    private void printRepaymentRows(List<MonthlyRepayment> monthlyRepayments) {
        for (MonthlyRepayment monthlyRepayment : monthlyRepayments) {
            printRow(
                    monthlyRepayment.dueDate(),
                    monthlyRepayment.remainingDept().negate(),
                    monthlyRepayment.interest(),
                    monthlyRepayment.repayment(),
                    monthlyRepayment.monthlyRate()
            );
        }

    }

    private void printSummary(RepaymentSummary summary) {
        printRow(
                "Zinsbindungsende",
                money(summary.remainingDebt().negate()),
                money(summary.totalInterest()),
                money(summary.totalRepayments()),
                money(summary.totalRates())
        );
    }

    private void printPaymentRow(CreditParameters creditParameters) {
        Money totalDebtNegated = creditParameters.totalDebt().negate();
        printRow(
                creditParameters.startingMonth(),
                totalDebtNegated,
                Money.zeroFor(totalDebtNegated.currency()),
                totalDebtNegated,
                totalDebtNegated
        );
    }

    private void printRow(LocalDate date, Money remainingDebt, Money interest, Money repayment, Money rate) {
        printRow(date(date), money(remainingDebt), money(interest), money(repayment), money(rate));
    }

    private String date(LocalDate date) {
        // not localized...
        return String.format("%1$02d.%2$02d.%3$4d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    private String money(Money money) {
        // Not really localized...
        return String.format(locale, "%1$.2f %2$s", money.amount(), money.currency().getSymbol(locale));
    }

    private void printRow(String date, String remainingDebt, String interest, String repayment, String rate) {
        printWriter.printf("%1$16s %2$12s %3$12s %4$25s %5$12s%n", date, remainingDebt, interest, repayment, rate);
    }

}
