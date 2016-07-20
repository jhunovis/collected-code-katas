package jhunovis.repaymentplan;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

/**
 * A Hamcrest matcher for {@link MonthlyRepayment} instances.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class IsMonthlyRepayment extends TypeSafeMatcher<MonthlyRepayment> {

    private final Collection<Matcher<MonthlyRepayment>> propertyMatchers;

    @SafeVarargs
    private IsMonthlyRepayment(Matcher<MonthlyRepayment>... propertyMatchers) {
        this.propertyMatchers = Arrays.asList(propertyMatchers);
    }

    @SafeVarargs
    public static IsMonthlyRepayment isRepaymentWith(Matcher<MonthlyRepayment>... fieldMatchers) {
        return new IsMonthlyRepayment(fieldMatchers);
    }

    public static Matcher<MonthlyRepayment> dueDate(int year, Month month, int dayOfMonth) {
        return new PropertyMatcher<>("dueDate", LocalDate.of(year, month, dayOfMonth), MonthlyRepayment::dueDate);
    }

    public static Matcher<MonthlyRepayment> remainingDebt(Money remainingDebt) {
        return new PropertyMatcher<>("remainingDebt", remainingDebt, MonthlyRepayment::remainingDept);
    }

    public static Matcher<MonthlyRepayment> interest(Money interest) {
        return new PropertyMatcher<>("interest", interest, MonthlyRepayment::interest);
    }

    public static Matcher<MonthlyRepayment> repayment(Money repayment) {
        return new PropertyMatcher<>("repayment", repayment, MonthlyRepayment::repayment);
    }

    public static Matcher<MonthlyRepayment> monthlyRate(Money monthlyRate) {
        return new PropertyMatcher<>("monthlyRate", monthlyRate, MonthlyRepayment::monthlyRate);
    }

    @Override
    protected boolean matchesSafely(MonthlyRepayment item) {
        return propertyMatchers.stream()
                .allMatch(p -> p.matches(item));
    }

    @Override
    public void describeTo(Description description) {
        description.appendList("MonthlyRepayment{", ", ", "}", propertyMatchers);
    }
}
