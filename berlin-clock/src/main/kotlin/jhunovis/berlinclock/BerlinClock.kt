package jhunovis.berlinclock

import java.time.LocalTime

/**
 * The Berlin Clock implemented in Kotlin.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class BerlinClock(val time: LocalTime) {

    val secondsLight: Boolean = time.second % 2 == 0

    val upperHourLights: List<Boolean> = listOf(time.hour > 4, time.hour > 9, time.hour > 14, time.hour > 19)

    val lowerHourLights: List<Boolean> = zeroToFourLights(time.hour)

    val upperMinuteLights: List<Boolean> = (1..11).map { time.minute - it*5 >= 0 }

    val lowerMinuteLights: List<Boolean> = zeroToFourLights(time.minute)

    private fun zeroToFourLights(timeComponent: Int) =
            listOf(
                    timeComponent % 5 >= 1,
                    timeComponent % 5 >= 2,
                    timeComponent % 5 >= 3,
                    timeComponent % 5 >= 4
            )
}