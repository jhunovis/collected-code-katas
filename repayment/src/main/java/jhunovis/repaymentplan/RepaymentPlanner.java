package jhunovis.repaymentplan;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Command line interface for computing repayment plans.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentPlanner {

    private final PrintStream out;
    private final CreditParametersFromConsole creditParametersFromConsole;

    public RepaymentPlanner() {
        this(new InputStreamReader(System.in), System.out);
    }

    RepaymentPlanner(Readable in, PrintStream out) {
        this.out = out;
        creditParametersFromConsole = new CreditParametersFromConsole(in, out);
    }

    public static void main(String... args) {
        new RepaymentPlanner().run();
    }

    void run() {
        CreditParameterBuilder creditParametersBuilder = readCreditParameters();
        RepaymentPlan repaymentPlan = creditParametersBuilder.createCreditParameters().createRepaymentPlan();
        printCreditDetails(repaymentPlan);
    }

    private CreditParameterBuilder readCreditParameters() {
        return creditParametersFromConsole.readCreditParametersFromConsole();
    }

    private void printCreditDetails(RepaymentPlan repaymentPlan) {
        printCreditParameters(repaymentPlan.creditParameters());
        printRepaymentPlan(repaymentPlan);
        println("Fertig!");
    }

    private void printCreditParameters(CreditParameters creditParameters) {
        println(
                String.format(
                        "Berechne Tilgungsplan ab %s mit Kreditsumme %s, Sollzins %s%% und Tilgung %s%% über %s Monate.",
                        creditParameters.startingMonth(), creditParameters.totalDebt(), creditParameters.interestRateInPercent(),
                        creditParameters.initialRepaymentRateInPercent(), creditParameters.durationInMonths()
                )
        );
    }

    private void printRepaymentPlan(RepaymentPlan repaymentPlan) {
        RepaymentPlanPrinter repaymentPlanPrinter = new RepaymentPlanPrinter(new PrintWriter(out), Locale.GERMANY);
        repaymentPlanPrinter.print(repaymentPlan);
    }

    private void println(String message) {
        print(message + "\n");
    }

    private void print(String message) {
        out.print(message);
    }

}
