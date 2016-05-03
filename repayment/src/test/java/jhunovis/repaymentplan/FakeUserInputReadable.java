package jhunovis.repaymentplan;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * For simulating user input on the command line. Fake user input is prepared through {@link #type(String)}
 * and {@link #garbage(String)}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
final class FakeUserInputReadable implements Readable {

    private final List<String> waitingUserInput = new LinkedList<>();

    @Override
    public int read(@NotNull CharBuffer cb) throws IOException {
        String userInput = waitingUserInput.remove(0);
        cb.append(userInput).append("\n");
        return userInput.length() + 1;
    }

    /**
     * Prepare fake user input which will be produced by this {@link Readable} whenever it is read.
     * Each call to {@link #read(CharBuffer)} will produce on of the items prepared through this very method.
     *
     * @param userInput fake user input
     * @return this
     */
    public FakeUserInputReadable type(String userInput) {
        waitingUserInput.add(userInput);
        return this;
    }

    /**
     * Alias for {@link #type(String)}.
     */
    public FakeUserInputReadable garbage(String wrongUserInput) {
        return type(wrongUserInput);
    }
}
