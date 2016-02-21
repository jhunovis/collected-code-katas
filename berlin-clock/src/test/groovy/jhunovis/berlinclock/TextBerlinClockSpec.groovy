package jhunovis.berlinclock

import spock.lang.Specification

import java.time.LocalTime

/**
 * A Spock specification of {@link TextBerlinClockKt}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class TextBerlinClockSpec extends Specification {

    def "text representations of the Berlin Clock"() {
        setup:
        def clock = new BerlinClock(LocalTime.of(hour, minute, second))

        expect:
        TextBerlinClockKt.asText(clock) == textRepresentation.stripMargin()

        where:
        hour | minute | second || textRepresentation
        0    | 0      | 0      ||"""Y
                                   |0000
                                   |0000
                                   |00000000000
                                   |0000"""
        12   | 22     | 12     ||"""Y
                                   |YY00
                                   |YY00
                                   |YYRY0000000
                                   |YY00"""
        23   | 59     | 59     ||"""0
                                   |YYYY
                                   |YYY0
                                   |YYRYYRYYRYY
                                   |YYYY"""
    }
}