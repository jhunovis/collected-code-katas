package jhunovis.datamunging;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Holds all the {@link WeatherDayRecord} instances of a month.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class WeatherMonthRecord {
    private final Map<Integer, WeatherDayRecord> reportsByDay;

    public WeatherMonthRecord(List<WeatherDayRecord> reports) {
        reportsByDay = reports.stream()
                .collect(Collectors.toMap(recordForKey -> recordForKey.day, recordForValue -> recordForValue));
    }

    /**
     * Get the weather report of the given day.
     *
     * @param day the day for which to get the record
     * @return the weather report of the given day
     */
    public final Optional<WeatherDayRecord> recordOfDay(int day) {
        return Optional.ofNullable(reportsByDay.get(day));
    }

    /**
     * @return all weather reports
     */
    public final Collection<WeatherDayRecord> dayRecords() {
        return reportsByDay.values();
    }
}
