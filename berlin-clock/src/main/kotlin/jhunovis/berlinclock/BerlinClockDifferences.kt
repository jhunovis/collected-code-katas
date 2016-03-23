package jhunovis.berlinclock

import java.util.*

/**
 * Compare the lights of two [BerlinClock] instances.
 *
 * @param otherClock the clock to compare with
 * @return a list of identifiers of the light which have been turned off from the receiving clock to  the argument clock.
 */
fun BerlinClock.javaFxIdsOfLightsSwitchedOff(otherClock : BerlinClock)
        = BerlinClockDifferences(this, otherClock).lightsSwitchedOff()

/**
 * Compare the lights of two [BerlinClock] instances.
 *
 * @param otherClock the clock to compare with
 * @return a list of identifiers of the light which have been turned on from the receiving clock to  the argument clock.
 */
fun BerlinClock.javaFxIdsOfLightsSwitchedOn(otherClock : BerlinClock)
        = BerlinClockDifferences(otherClock, this).lightsSwitchedOff()

/**
 * Compare two [BerlinClock] instances. Produces a list of identifiers of the lights that went off between [oldClock]
 * and [newClock]. (By swapping the two arguments one can find the lights turned on.)
 *
 * The identifiers are uniformly build with this pattern `<ROW>-<NUMBER>` where `<ROW>` is one of `seconds`, `minutes`,
 * `five-minutes`, `hours` and `five-hours`. The `<ROW>` is the number of the light the row of lights counting from
 * left to right and starting with one. Examples: `five-minutes-2`, `seconds-1`.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class BerlinClockDifferences(val oldClock: BerlinClock, val newClock: BerlinClock) {

    /**
     * Return the identification of each light that went off from [oldClock] to [newClock].
     * This method can also be used to find the lights that went on by simply swapping [oldClock] and [newClock]
     * passed to the constructor.
     */
    fun lightsSwitchedOff(): List<String> {
        val lightsOff = LinkedList<String>()
        lightsOff.addAll(findSwitchOffSeconds())
        lightsOff.addAll(findSwitchedOffLowerMinuteLights())
        lightsOff.addAll(findSwitchedOffUpperMinuteLights())
        lightsOff.addAll(findSwitchedOffLowerHourLights())
        lightsOff.addAll(findSwitchedOffUpperHourLights())
        return lightsOff
    }

    private fun findSwitchedOffUpperHourLights()
            = findLightsSwitchedOffAndMapToLightBulbViewId(oldClock.upperHourLights, newClock.upperHourLights, "five-hours")

    private fun findSwitchedOffLowerHourLights()
            = findLightsSwitchedOffAndMapToLightBulbViewId(oldClock.lowerHourLights, newClock.lowerHourLights, "hours")

    private fun findSwitchedOffUpperMinuteLights()
            = findLightsSwitchedOffAndMapToLightBulbViewId(oldClock.upperMinuteLights, newClock.upperMinuteLights, "five-minutes")

    private fun findSwitchedOffLowerMinuteLights()
            = findLightsSwitchedOffAndMapToLightBulbViewId(oldClock.lowerMinuteLights, newClock.lowerMinuteLights, "minutes")

    private fun findSwitchOffSeconds()
            = findLightsSwitchedOffAndMapToLightBulbViewId(listOf(oldClock.secondsLight), listOf(newClock.secondsLight), "seconds")

    private fun findLightsSwitchedOffAndMapToLightBulbViewId(oldClockLights: List<Boolean>, newClockLights: List<Boolean>, prefix: String)
            = findIndicesOfLightsTurnedOff(oldClockLights, newClockLights).map { prefix + "-" + (it  + 1) }

    private fun findIndicesOfLightsTurnedOff(oldClockLights: List<Boolean>, newClockLights: List<Boolean>)
            = oldClockLights.indices.filter { oldClockLights[it] && !newClockLights[it] }

}

