package jhunovis.repaymentplan;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.function.Function;

/**
 * A matcher which tests that a property of an object has a certain value. The property needs not to be a bean getter.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public class PropertyMatcher<P, T> extends TypeSafeMatcher<T>{

    private final P propertyValue;
    private final String propertyName;
    private final Function<T, P> propertySupplier;

    /**
     * @param propertyName the name of the property to access (for the description)
     * @param propertyValue the value expected for the property
     * @param propertySupplier a function providing the actual value of the property from the containing object
     */
    public PropertyMatcher(String propertyName, P propertyValue, Function<T, P> propertySupplier) {
        this.propertyValue = propertyValue;
        this.propertyName = propertyName;
        this.propertySupplier = propertySupplier;
    }

    @Override
    protected boolean matchesSafely(T item) {
        return propertyValue.equals(propertySupplier.apply(item));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(propertyName).appendText("=").appendValue(propertyValue);
    }
}
