package jhunovis.repaymentplan;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.regex.Pattern;

/**
 * Hamcrest matcher matching by regular expression.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class IsMatchingRegex extends BaseMatcher<CharSequence>{

    private final Pattern regEx;

    private IsMatchingRegex(String pattern) {
        regEx = Pattern.compile(pattern);
    }

    public static IsMatchingRegex matches(String pattern) {
        return new IsMatchingRegex(pattern);
    }

    @Override
    public boolean matches(Object item) {
        if (item instanceof CharSequence) {
            return regEx.matcher((CharSequence)item).find();
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("text matched by regular expression ").appendValue(regEx.toString());
    }
}
