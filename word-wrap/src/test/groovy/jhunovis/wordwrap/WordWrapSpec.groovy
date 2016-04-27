package jhunovis.wordwrap

import spock.lang.Specification
import spock.lang.Unroll

/**
 * A Spock specification for Word-Wrap code kata implementations.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class WordWrapSpec extends Specification {

    @Unroll("Whitespace splits: split #text at column #column should be #splitText")
    def "Exact hits at space"() {
        expect:
        new WordWrapJava(column).wrap(text) == splitText

        where:
        text             | column || splitText
        ""               | 5      || []
        "AAAA"           | 4      || ["AAAA\n"]
        "AAAA BBBB CCCC" | 4      || ["AAAA\n", "BBBB\n", "CCCC\n"]

    }

    def "Multiple subsequent space are removed"() {
        expect:
        new WordWrapJava(column).wrap(text) == splitText

        where:
        text             | column || splitText
        "   "            | 5      || []
        "  A   A  "          | 5      || ["A   A\n"]
    }

    @Unroll("Intra-word splits: split #text at column #column should be #splitText")
    def "Intra-word splits"() {
        expect:
        new WordWrapJava(column).wrap(text) == splitText

        where:
        text             | column || splitText
        "AAAA"           | 2      || ["AA\n", "AA\n"]
        "AAAA BBBB CCCC" | 2      || ["AA\n", "AA\n", "BB\n", "BB\n", "CC\n", "CC\n"]
    }

    @Unroll("Real-word splits: split #text at column #column should be #splitText")
    def "Real-world splits"() {
        expect:
        new WordWrapJava(column).wrap(text) == splitText

        where:
        text                                                              | column || splitText
        "No word in this text is longer than the column width."           | 7      || ["No word\n", "in this\n", "text is\n",
                                                                                       "longer\n", "than\n", "the\n",
                                                                                       "column\n", "width.\n"]
        "One extravagant word will not fit." | 9      || ["One\n", "extravaga\n", "nt word\n", "will not\n", "fit.\n"]
    }

    def "Split lines should end on newline"() {
        expect:
        new WordWrapJava(column).wrap(text).every {it.endsWith("\n")}

        where:
        text               | column || splitText
        ""                 | 1      || _
        "Line with spaces" | 4      || _
        "AAA BBB CCC DDD"  | 3      || _
    }
}
