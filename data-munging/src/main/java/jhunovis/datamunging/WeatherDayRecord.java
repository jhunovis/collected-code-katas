package jhunovis.datamunging;

/**
 * A weather record for one day of month.
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public class WeatherDayRecord {

    public final int day;
    public final double minTemperature;
    public final double maxTemperature;

    public WeatherDayRecord(int day, double minTemperature, double maxTemperature) {
        this.day = day;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDayRecord that = (WeatherDayRecord) o;

        if (day != that.day) return false;
        if (Double.compare(that.minTemperature, minTemperature) != 0) return false;
        return Double.compare(that.maxTemperature, maxTemperature) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = day;
        temp = Double.doubleToLongBits(minTemperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxTemperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDayRecord{" +
                "day=" + day +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                '}';
    }
}
