package jhunovis.repaymentplan;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Read credit parameters from the command line.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
final class CreditParametersFromConsole {

    private final Readable in;
    private final PrintStream out;

    private Scanner scanner;

    public CreditParametersFromConsole(Readable in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Ask the user to enter credit parameters.
     */
    public CreditParameterBuilder readCreditParametersFromConsole() {
        println("Eingabe der Kreditparameter");

        CreditParameterBuilder builder = CreditParameters.builder();
        readStartDate(builder::starting);
        readCreditVolume(builder::creditVolume);
        readInterestRateInPercent(builder::interestRateInPercent);
        readRepaymentRateInPercent(builder::repaymentRateInPercent);
        readDurationInMonths(builder::durationInYears);

        return builder;
    }

    private void readStartDate(Consumer<LocalDate> setter) {
        readWithRetry("Startdatum (MM.YYYY), z.B. 05.2016: ", this::readDate, setter);
    }

    private void readCreditVolume(Consumer<Money> setter) {
        readWithRetry("Kreditsumme in €, z.B. 5000,00: ", this::readAmount, setter);
    }

    private void readInterestRateInPercent(Consumer<BigDecimal> setter) {
        readWithRetry("Sollzins in Prozent, z.B. 2,05: ", this::readDecimal, setter);
    }

    private void readRepaymentRateInPercent(Consumer<BigDecimal> setter) {
        readWithRetry("Tilgung in Prozent, z.B. 5,00: ", this::readDecimal, setter);
    }

    private void readDurationInMonths(Consumer<Integer> setter) {
        readWithRetry("Laufzeit in Jahren, z.B. 3: ", this::readNumber, setter);
    }

    private int readNumber() {
        return scanner.nextInt();
    }

    private LocalDate readDate() {
        String userInput = scanner.nextLine();
        return LocalDate.parse("01." + userInput, DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }

    private Money readAmount() {
        return Money.euro(readDecimal().toString());
    }

    private BigDecimal readDecimal() {
        String userInput = scanner.next("\\d+((,\\d\\d)?|(,\\d)?)");
        return new BigDecimal(userInput.replace(",", "."));
    }

    private <T> void readWithRetry(String message, Supplier<T> userInputReader, Consumer<T> builderSetter) {
        scanner = new Scanner(in);
        do {
            try {
                print(message);
                T userInput = userInputReader.get();
                builderSetter.accept(userInput);
                break;
            } catch (Exception e) {
                println("Eingabe ungültig!");
                scanner = new Scanner(in);
            }
        } while (true);
    }

    private void println(String message) {
        print(message + "\n");
    }

    private void print(String message) {
        out.print(message);
    }

}
