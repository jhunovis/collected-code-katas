package jhunovis.berlinclock

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

/**
 * A Spock specification {@link BerlinClockDifferences}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class BerlinClockDifferencesSpec extends Specification {

    @Unroll
    def "test lightsSwitchedOff"() {
        setup:
        def differences = BerlinClockDifferencesKt.javaFxIdsOfLightsSwitchedOff(new BerlinClock(oldTime), new BerlinClock(newTime))

        expect:
            differences.sort() == lightsSwitchedOff.sort()

        where:
        oldTime                 | newTime                  || lightsSwitchedOff
        LocalTime.of(0, 0, 0)   | LocalTime.of(0, 0, 1)    || ["seconds_1"]
        LocalTime.of(0, 1, 0)   | LocalTime.of(0, 0, 0)    || ["minutes_1"]
        LocalTime.of(0, 5, 0)   | LocalTime.of(0, 0, 0)    || ["fiveMinutes_1"]
        LocalTime.of(1, 0, 0)   | LocalTime.of(0, 0, 0)    || ["hours_1"]
        LocalTime.of(5, 0, 0)   | LocalTime.of(0, 0, 0)    || ["fiveHours_1"]
        LocalTime.of(23, 59, 0) | LocalTime.of(0, 0, 1)    || ["fiveHours_1", "fiveHours_2", "fiveHours_3", "fiveHours_4",
                                                               "hours_1", "hours_2", "hours_3",
                                                               "fiveMinutes_1", "fiveMinutes_2", "fiveMinutes_3", "fiveMinutes_4",
                                                               "fiveMinutes_5", "fiveMinutes_6", "fiveMinutes_7", "fiveMinutes_8",
                                                               "fiveMinutes_9", "fiveMinutes_10", "fiveMinutes_11",
                                                               "minutes_1", "minutes_2", "minutes_3", "minutes_4",
                                                               "seconds_1"]
    }

    @Unroll
    def "test lightsSwitchedOn"() {
        setup:
        def differences = BerlinClockDifferencesKt.javaFxIdsOfLightsSwitchedOn(new BerlinClock(oldTime), new BerlinClock(newTime))

        expect:
        differences.sort() == lightsSwitchedOn.sort()

        where:
        oldTime               | newTime                 || lightsSwitchedOn
        LocalTime.of(0, 0, 1) | LocalTime.of(0, 0, 0)   || ["seconds_1"]
        LocalTime.of(0, 0, 0) | LocalTime.of(0, 1, 0)   || ["minutes_1"]
        LocalTime.of(0, 0, 0) | LocalTime.of(0, 5, 0)   || ["fiveMinutes_1"]
        LocalTime.of(0, 0, 0) | LocalTime.of(1, 0, 0)   || ["hours_1"]
        LocalTime.of(0, 0, 0) | LocalTime.of(5, 0, 0)   || ["fiveHours_1"]
        LocalTime.of(0, 0, 1) | LocalTime.of(23, 59, 0) || ["fiveHours_1", "fiveHours_2", "fiveHours_3", "fiveHours_4",
                                                            "hours_1", "hours_2", "hours_3",
                                                            "fiveMinutes_1", "fiveMinutes_2", "fiveMinutes_3", "fiveMinutes_4",
                                                            "fiveMinutes_5", "fiveMinutes_6", "fiveMinutes_7", "fiveMinutes_8",
                                                            "fiveMinutes_9", "fiveMinutes_10", "fiveMinutes_11",
                                                            "minutes_1", "minutes_2", "minutes_3", "minutes_4",
                                                            "seconds_1"]
    }
}
