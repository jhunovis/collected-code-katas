package jhunovis.repaymentplan;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * A unit-test for {@link RepaymentPlanPrinter}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentPlanPrinterTest {

    private final RepaymentPlan repaymentPlan = RepaymentPlan.builder()
            .starting(LocalDate.of(2016, Month.MAY, 31))
            .creditVolume(Money.euro("1000.00"))
            .interestRateInPercent("5.00")
            .repaymentRateInPercent("10.00")
            .durationInMonths(5)
            .createRepaymentPlan();

    private final StringWriter capturedPrintOut = new StringWriter();
    private final RepaymentPlanPrinter repaymentPlanPrinter = new RepaymentPlanPrinter(
            new PrintWriter(capturedPrintOut), Locale.GERMANY);

    @Test
    public void printPlan_ShouldCreateHeaderLine() throws Exception {
        repaymentPlanPrinter.print(repaymentPlan);

        assertThat(
                capturedPrintLines().get(0),
                row("Datum", "Restschuld", "Zinsen", "Tilgung(+)/Auszahlung(-)", "Rate")
        );
    }

    @Test
    public void printPlan_ShouldCreateInitialPaymentLine() throws Exception {
        repaymentPlanPrinter.print(repaymentPlan);

        assertThat(
                capturedPrintLines().get(1),
                row("31.05.2016", "-1000,00 €", "0,00 €", "-1000,00 €", "-1000,00 €")
        );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void printPlan_ShouldRowsForEachMonth() throws Exception {
        repaymentPlanPrinter.print(repaymentPlan);

        assertThat(
                capturedPrintLines().subList(2, 7),
                IsIterableContainingInOrder.contains(
                        row("30.06.2016", "-991,67 €", "4,17 €", "8,33 €", "12,50 €"),
                        row("31.07.2016", "-983,30 €", "4,13 €", "8,37 €", "12,50 €"),
                        row("31.08.2016", "-974,90 €", "4,10 €", "8,40 €", "12,50 €"),
                        row("30.09.2016", "-966,46 €", "4,06 €", "8,44 €", "12,50 €"),
                        row("31.10.2016", "-957,99 €", "4,03 €", "8,47 €", "12,50 €")
                )
        );
    }

    @Test
    public void printPlan_ShouldCreateSummaryLine() throws Exception {
        repaymentPlanPrinter.print(repaymentPlan);

        assertThat(
                capturedPrintLines().get(7),
                row("Zinsbindungsende", "-957,99 €", "20,49 €", "42,01 €", "62,50 €")
        );
    }


    private List<String> capturedPrintLines() {
        return Arrays.asList(capturedPrintOut.toString().split("\n"));
    }

    @NotNull
    private Matcher<String> row(String dateColumn, String remainingDebtColumn, String interestColumn, String repaymentColumn, String rateColumn) {
        // this is not perfect as it does guarantee any order
        return CoreMatchers.allOf(
                containsString(dateColumn),
                containsString(remainingDebtColumn),
                containsString(interestColumn),
                containsString(repaymentColumn),
                containsString(rateColumn)
        );
    }
}