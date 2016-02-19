package jhunovis.berlinclock;

import spock.lang.Specification

import java.time.LocalTime

/**
 * A Spock specification of the "Berlin Clock".
 *
 * @see {@link jhunovis.berlinclock.BerlinClock}
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class BerlinClockSpec extends Specification {
    def "seconds light shines yellow (Y) for every even second"() {
        expect:
        new BerlinClock(LocalTime.of(0, 0, seconds)).secondsLight

        where:
        seconds << [0, 2, 4, 6, 8, 10, 20, 32, 58]
    }

    def "seconds light is off (O) for every odd second"() {
        expect:
        !new BerlinClock(LocalTime.of(0, 0, seconds)).secondsLight

        where:
        seconds << [1, 3, 5, 7, 11, 21, 31, 59]
    }

    def "Each of the 4 lights in UPPER HOUR ROW stands for five hours. Counting from left to right."() {
        expect:
        new BerlinClock(LocalTime.of(hours, 0, 0)).upperHourLights == expectedLights

        where: "false = light is off"
        hours|| expectedLights
        0    || [false, false, false, false]
        4    || [false, false, false, false]
        5    || [true, false, false, false]
        9    || [true, false, false, false]
        10   || [true, true, false, false]
        14   || [true, true, false, false]
        15   || [true, true, true, false]
        19   || [true, true, true, false]
        20   || [true, true, true, true]
        23   || [true, true, true, true]
    }

    def "Each of the 4 lights in LOWER HOUR ROW stands for one hours. Beginning to count from left to right."() {
        expect:
        new BerlinClock(LocalTime.of(hours, 0, 0)).lowerHourLights == expectedLights

        where: "false = light is off"
        hours|| expectedLights
         0   || [false, false, false, false]
        10   || [false, false, false, false]
        20   || [false, false, false, false]
         1   || [true, false, false, false]
        11   || [true, false, false, false]
        21   || [true, false, false, false]
         2   || [true, true, false, false]
        12   || [true, true, false, false]
        22   || [true, true, false, false]
         3   || [true, true, true, false]
        13   || [true, true, true, false]
        23   || [true, true, true, false]
         4   || [true, true, true, true]
        14   || [true, true, true, true]
    }

    def "Each of the 11 lights in UPPER MINUTE ROW stands for five minutes. Counting from left to right."() {
        expect:
        new BerlinClock(LocalTime.of(0, minutes, 0)).upperMinuteLights == expectedLights

        where: "false = light is off"
        minutes || expectedLights
         0      || [false, false, false, false, false, false, false, false, false, false, false]
         4      || [false, false, false, false, false, false, false, false, false, false, false]
         5      || [true, false, false, false, false, false, false, false, false, false, false]
         9      || [true, false, false, false, false, false, false, false, false, false, false]
        10      || [true, true, false, false, false, false, false, false, false, false, false]
        14      || [true, true, false, false, false, false, false, false, false, false, false]
        15      || [true, true, true, false, false, false, false, false, false, false, false]
        19      || [true, true, true, false, false, false, false, false, false, false, false]
        20      || [true, true, true, true, false, false, false, false, false, false, false]
        24      || [true, true, true, true, false, false, false, false, false, false, false]
        25      || [true, true, true, true, true, false, false, false, false, false, false]
        29      || [true, true, true, true, true, false, false, false, false, false, false]
        30      || [true, true, true, true, true, true, false, false, false, false, false]
        34      || [true, true, true, true, true, true, false, false, false, false, false]
        35      || [true, true, true, true, true, true, true, false, false, false, false]
        39      || [true, true, true, true, true, true, true, false, false, false, false]
        40      || [true, true, true, true, true, true, true, true, false, false, false]
        44      || [true, true, true, true, true, true, true, true, false, false, false]
        45      || [true, true, true, true, true, true, true, true, true, false, false]
        49      || [true, true, true, true, true, true, true, true, true, false, false]
        50      || [true, true, true, true, true, true, true, true, true, true, false]
        54      || [true, true, true, true, true, true, true, true, true, true, false]
        55      || [true, true, true, true, true, true, true, true, true, true, true]
        59      || [true, true, true, true, true, true, true, true, true, true, true]
    }

    def "Each of the 4 lights in LOWER MINUTE ROW stands for one minute. Beginning to count from left to right."() {
        expect:
        new BerlinClock(LocalTime.of(0, minutes, 0)).lowerMinuteLights == expectedLights

        where: "false = light is off"
        minutes || expectedLights
         0      || [false, false, false, false]
         5      || [false, false, false, false]
        20      || [false, false, false, false]
        25      || [false, false, false, false]
        30      || [false, false, false, false]
        35      || [false, false, false, false]
        40      || [false, false, false, false]
        45      || [false, false, false, false]
        50      || [false, false, false, false]
        55      || [false, false, false, false]
         1      || [true, false, false, false]
         6      || [true, false, false, false]
        11      || [true, false, false, false]
        16      || [true, false, false, false]
        41      || [true, false, false, false]
        46      || [true, false, false, false]
         2      || [true, true, false, false]
         7      || [true, true, false, false]
        32      || [true, true, false, false]
        37      || [true, true, false, false]
         3      || [true, true, true, false]
         8      || [true, true, true, false]
        53      || [true, true, true, false]
        58      || [true, true, true, false]
         4      || [true, true, true, true]
         9      || [true, true, true, true]
        54      || [true, true, true, true]
        59      || [true, true, true, true]
    }

}
