package jhunovis.datamunging;

import java.util.ArrayList;
import java.util.List;

/**
 * Programmatically create sample data of kink {@link WeatherMonthRecord} and {@link WeatherDayRecord} for testing.
 *
 * Can be worked as a builder by adding new days through {@link #recordDayWithMinMax(double, double)} and building
 * with {@link #report()}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
final class WeatherTestDataCreator {
    private List<WeatherDayRecord> weatherRecords = new ArrayList<>();

    /**
     * Add the weather record for a day.
     *
     * @return this builder
     */
    public WeatherTestDataCreator recordDayWithMinMax(double minTemp, double maxTemp) {
        weatherRecords.add(createDayWithMinMax(weatherRecords.size()+1, minTemp, maxTemp));
        return this;
    }

    /**
     * Create a {@link WeatherMonthRecord} from the days added through {@link #recordDayWithMinMax(double, double)}.
     *
     * @return a monthly weather record
     */
    public WeatherMonthRecord report() {
        return new WeatherMonthRecord(weatherRecords);
    }

    /**
     * Create an instance of {@link WeatherDayRecord}. Useful for assertions.
     *
     * @return a weather record for a day
     */
    public WeatherDayRecord createDayWithMinMax(int day, double minTemperature, double maxTemperature) {
        return new WeatherDayRecord(day, minTemperature, maxTemperature);
    }
}
