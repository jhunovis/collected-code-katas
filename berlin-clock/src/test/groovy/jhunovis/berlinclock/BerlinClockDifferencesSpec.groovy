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
        LocalTime.of(0, 0, 0)   | LocalTime.of(0, 0, 1)    || ["seconds-1"]
        LocalTime.of(0, 1, 0)   | LocalTime.of(0, 0, 0)    || ["minutes-1"]
        LocalTime.of(0, 5, 0)   | LocalTime.of(0, 0, 0)    || ["five-minutes-1"]
        LocalTime.of(1, 0, 0)   | LocalTime.of(0, 0, 0)    || ["hours-1"]
        LocalTime.of(5, 0, 0)   | LocalTime.of(0, 0, 0)    || ["five-hours-1"]
        LocalTime.of(23, 59, 0) | LocalTime.of(0, 0, 1)    || ["five-hours-1", "five-hours-2", "five-hours-3", "five-hours-4",
                                                               "hours-1", "hours-2", "hours-3",
                                                               "five-minutes-1", "five-minutes-2", "five-minutes-3", "five-minutes-4",
                                                               "five-minutes-5", "five-minutes-6", "five-minutes-7", "five-minutes-8",
                                                               "five-minutes-9", "five-minutes-10", "five-minutes-11",
                                                               "minutes-1", "minutes-2", "minutes-3", "minutes-4",
                                                               "seconds-1"]
    }

    @Unroll
    def "test lightsSwitchedOn"() {
        setup:
        def differences = BerlinClockDifferencesKt.javaFxIdsOfLightsSwitchedOn(new BerlinClock(oldTime), new BerlinClock(newTime))

        expect:
        differences.sort() == lightsSwitchedOn.sort()

        where:
        oldTime               | newTime                 || lightsSwitchedOn
        LocalTime.of(0, 0, 1) | LocalTime.of(0, 0, 0)   || ["seconds-1"]
        LocalTime.of(0, 0, 0) | LocalTime.of(0, 1, 0)   || ["minutes-1"]
        LocalTime.of(0, 0, 0) | LocalTime.of(0, 5, 0)   || ["five-minutes-1"]
        LocalTime.of(0, 0, 0) | LocalTime.of(1, 0, 0)   || ["hours-1"]
        LocalTime.of(0, 0, 0) | LocalTime.of(5, 0, 0)   || ["five-hours-1"]
        LocalTime.of(0, 0, 1) | LocalTime.of(23, 59, 0) || ["five-hours-1", "five-hours-2", "five-hours-3", "five-hours-4",
                                                            "hours-1", "hours-2", "hours-3",
                                                            "five-minutes-1", "five-minutes-2", "five-minutes-3", "five-minutes-4",
                                                            "five-minutes-5", "five-minutes-6", "five-minutes-7", "five-minutes-8",
                                                            "five-minutes-9", "five-minutes-10", "five-minutes-11",
                                                            "minutes-1", "minutes-2", "minutes-3", "minutes-4",
                                                            "seconds-1"]
    }
}
