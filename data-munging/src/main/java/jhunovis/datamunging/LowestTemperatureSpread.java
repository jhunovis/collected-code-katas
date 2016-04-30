package jhunovis.datamunging;

import java.util.Optional;

/**
 * Compute the "lowest temperature spread" over a given monthly weather report, i.e. the day with the smallest
 * distance between the minimal and the maximal day temperature.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class LowestTemperatureSpread {

    public final WeatherDayRecord findLowestTemperatureSpread(WeatherMonthRecord weatherMonthRecord) {
        Optional<WeatherDayRecord> lowestSpread = weatherMonthRecord.dayRecords().stream().min(
                (oneDay, otherDay) -> new Double(spread(oneDay)).compareTo(spread(otherDay)));
        return lowestSpread.orElseThrow(() -> new IllegalStateException("No daily weather records found!"));
    }

    private double spread(WeatherDayRecord oneDay) {
        return oneDay.maxTemperature - oneDay.minTemperature;
    }

}
