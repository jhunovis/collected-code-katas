package jhunovis.datamunging;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * A unit-test for {@link LowestTemperatureSpread}.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public class LowestTemperatureSpreadTest {

    private WeatherTestDataCreator weatherTestDataCreator = new WeatherTestDataCreator();

    @Test
    public void findLowestTemperatureSpread() throws Exception {
        WeatherMonthRecord report = weatherTestDataCreator
                .recordDayWithMinMax(10.0, 20.0)
                .recordDayWithMinMax(15.0, 20.0)
                .recordDayWithMinMax(9.0, 20.0)
                .report();

        assertThat(
                new LowestTemperatureSpread().findLowestTemperatureSpread(report),
                is(equalTo(report.recordOfDay(2).orElse(null)))
        );
    }

    @Test(expected = IllegalStateException.class)
    public void findLowestTemperatureSpread_GivenNoWeatherReports_ShouldThrowExcpetion() throws Exception {
        WeatherMonthRecord emptyReport = weatherTestDataCreator.report();

        assertThat(
                new LowestTemperatureSpread().findLowestTemperatureSpread(emptyReport),
                is(equalTo(emptyReport.recordOfDay(2)))
        );
    }


}