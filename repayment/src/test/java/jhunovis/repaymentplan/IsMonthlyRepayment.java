package jhunovis.repaymentplan;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.time.LocalDate;

/**
 * A Hamcrest matcher for {@link MonthlyRepayment} instances.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class IsMonthlyRepayment extends BaseMatcher<MonthlyRepayment> {

    private final LocalDate dueDate;
    private final Money remainingDebt;
    private final Money dueInterest;
    private final Money repayment;
    private final Money rate;

    public IsMonthlyRepayment(LocalDate dueDate, Money remainingDebt, Money dueInterest, Money repayment, Money rate) {
        this.dueDate = dueDate;
        this.remainingDebt = remainingDebt;
        this.dueInterest = dueInterest;
        this.repayment = repayment;
        this.rate = rate;
    }

    public static IsMonthlyRepayment isRepaymentWith(LocalDate dueDate, Money remainingDebt, Money dueInterest, Money repayment, Money rate) {
        return new IsMonthlyRepayment(dueDate, remainingDebt, dueInterest, repayment, rate);
    }

    @Override
    public boolean matches(Object item) {
        if (item instanceof MonthlyRepayment) {
            MonthlyRepayment repayment = (MonthlyRepayment) item;
            return repayment.dueDate().equals(dueDate)
                    && repayment.remainingDept().equals(remainingDebt)
                    && repayment.interest().equals(dueInterest)
                    && repayment.repayment().equals(this.repayment)
                    && repayment.monthlyRate().equals(rate);
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("MonthlyRepayment{dueDate=").appendValue(dueDate)
                .appendText(" remainingDebt=").appendValue(remainingDebt)
                .appendText(" interest=").appendValue(dueInterest)
                .appendText(" repayment=").appendValue(repayment)
                .appendText(" rate=").appendValue(rate)
                .appendText("}");
    }
}
