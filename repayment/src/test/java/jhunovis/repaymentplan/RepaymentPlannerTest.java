package jhunovis.repaymentplan;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static jhunovis.repaymentplan.IsMatchingRegex.matches;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertThat;

/**
 * A unit-test for {@link RepaymentPlanner} and {@link CreditParametersFromConsole}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class RepaymentPlannerTest {

    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    private final PrintStream out = new PrintStream(outputCapture);
    private final FakeUserInputReadable fakeUserInput = new FakeUserInputReadable();

    @Test
    public void uiShouldPrintCreditParameters() throws Exception {
        fakeUserInput.type("05.2015").type("1000,00").type("2,00").type("3,00").type("1");

        new RepaymentPlanner(fakeUserInput, out).run();

        assertThat(
                outputCapture.toString(),
                matches(".*Tilgungsplan.*2015-05-31.*1000.00 EUR.*2.00%.*3.00%.*12 Monate.")
        );
    }

    @Test
    public void uiShouldPrintRepaymentLines() throws Exception {
        fakeUserInput.type("05.2015").type("1000,00").type("2,00").type("3,00").type("1");

        new RepaymentPlanner(fakeUserInput, out).run();

        assertThat(
                outputCapture.toString(),
                allOf(
                        matches("30.09.2015.*-989,96 €.*1,65 €.*2,52 €.*4,17 €"),
                        matches("31.01.2016.*-979,86 €.*1,64 €.*2,53 €.*4,17 €")
                )
        );
    }

    @Test
    public void uiShouldNotChokeOnWrongInput() throws Exception {
        fakeUserInput
                .garbage("Mai 2015").type("05.2015")
                .garbage("1000.00€").type("1000,00")
                .garbage("1,00%").type("2,00")
                .garbage("103,00").type("3,00")
                .garbage("Hallo").type("1");

        new RepaymentPlanner(fakeUserInput, out).run();

        assertThat(
                outputCapture.toString(),
                matches("Fertig")
        );
    }

}