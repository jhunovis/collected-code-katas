package jhunovis.datamunging;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * A unit-test for {@link WeatherMonthRecord}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public class WeatherMonthRecordTest {

    private final WeatherTestDataCreator weatherTestDataCreator = new WeatherTestDataCreator();
    private final WeatherMonthRecord sampleWeatherReport;

    public WeatherMonthRecordTest() {
        sampleWeatherReport = weatherTestDataCreator
                .recordDayWithMinMax(1.0, 1.1)
                .recordDayWithMinMax(2.0, 2.1)
                .recordDayWithMinMax(3.0, 3.1)
                .report();
    }

    @Test
    public void testGetRecordForDay_1() throws Exception {
        assertThat(
                sampleWeatherReport.recordOfDay(1).orElse(null),
                is(equalTo(weatherTestDataCreator.createDayWithMinMax(1, 1.0, 1.1)))
        );
    }

    @Test
    public void testGetRecordForDay_2() throws Exception {
        assertThat(
                sampleWeatherReport.recordOfDay(2).orElse(null),
                is(equalTo(weatherTestDataCreator.createDayWithMinMax(2, 2.0, 2.1)))
        );
    }

    @Test
    public void testGetRecordForDay_3() throws Exception {
        assertThat(
                sampleWeatherReport.recordOfDay(3).orElse(null),
                is(equalTo(weatherTestDataCreator.createDayWithMinMax(3, 3.0, 3.1)))
        );
    }
}