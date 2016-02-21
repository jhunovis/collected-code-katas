package jhunovis.berlinclock

/**
 * Create a text representation of a [BerlinClock].
 *
 * The text consists of 5 lines:
 *
 * 1. One character. Either "Y" (yellow) or "0" (off). "Y" the seconds light of the clock is on, "0" if it is off.
 *    See [BerlinClock#secondsLight].
 * 2. Four characters, again, yellow or off for each light represented by [BerlinClock#upperHourLights]
 * 3. Same as 2. but from  [BerlinClock#lowerHourLights].
 * 4. Eleven characters, again, yellow or off for each light from [BerlinClock#upperMinuteLights] with the
 *    exception the 3rd, 6th, and 9th light shine red "R" when on. These mark full quarters of an hour.
 * 5. Four characters representing [BerlinClock#lowerMinuteLights]. Same as the second and third row of lights.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
fun BerlinClock.asText() =
        StringBuilder()
                .append(singleYellowLight(secondsLight)).append("\n")
                .append(rowOfAllYellowLights(upperHourLights)).append("\n")
                .append(rowOfAllYellowLights(lowerHourLights)).append("\n")
                .append(rowOfYellowAndRedLights(upperMinuteLights)).append("\n")
                .append(rowOfAllYellowLights(lowerMinuteLights))
                .toString()

fun singleYellowLight(lightStatus: Boolean) = if (lightStatus) "Y" else "0"

fun rowOfAllYellowLights(lightsStatus: List<Boolean>) = lightsStatus.joinToString(separator = "") { if (it) "Y" else "0" }

fun rowOfYellowAndRedLights(lightsStatus: List<Boolean>) =
        (1..11).map {
            if (lightsStatus[it-1])
                if (it % 3 == 0) "R" else "Y"
            else "0"
        }.joinToString(separator = "")
