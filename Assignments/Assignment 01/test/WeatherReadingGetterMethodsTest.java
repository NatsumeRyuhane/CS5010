package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import weather.WeatherReading;

public class WeatherReadingGetterMethodsTest {
    // This test class is for testing the getter methods of the WeatherReading class.
    // as the requirement does not specify test cases, we will allow a 0.01 tolerance for the expected values
    // for the calculated heat index and wind chill

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getterMethodTest1() {
        WeatherReading wr1 = new WeatherReading(10, 5, 10, 5);
        assertEquals(10, wr1.getTemperature());
        assertEquals(5, wr1.getDewPoint());
        assertEquals(10, wr1.getWindSpeed());
        assertEquals(5, wr1.getTotalRain());
        assertEquals(75, wr1.getRelativeHumidity());
        assertEquals(34.89, wr1.getHeatIndex(), 0.01);
        assertEquals(30.61, wr1.getWindChill(), 0.01);
    }

    @Test
    public void getterMethodTest2() {
        WeatherReading wr2 = new WeatherReading(30, 24, 17, 8);
        assertEquals(30, wr2.getTemperature());
        assertEquals(24, wr2.getDewPoint());
        assertEquals(17, wr2.getWindSpeed());
        assertEquals(8, wr2.getTotalRain());
        assertEquals(70, wr2.getRelativeHumidity());
        assertEquals(35.03, wr2.getHeatIndex(), 0.01);
        assertEquals(42.47, wr2.getWindChill(), 0.01);
    }

    // testing for borderline values of wind speed and total rain
    @Test
    public void getterMethodTest3() {
        WeatherReading wr3 = new WeatherReading(17, 16, 0, 0);
        assertEquals(17, wr3.getTemperature());
        assertEquals(16, wr3.getDewPoint());
        assertEquals(0, wr3.getWindSpeed());
        assertEquals(0, wr3.getTotalRain());
        assertEquals(95, wr3.getRelativeHumidity());
        assertEquals(15.68, wr3.getHeatIndex(), 0.01);
        assertEquals(61.49, wr3.getWindChill(), 0.01);
    }
}
