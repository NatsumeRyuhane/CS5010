package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import weather.WeatherReading;

public class WeatherReadingParametersTest {
    // This test class is for testing the constructor of the WeatherReading class.
    // If the passed-in parameters are invalid, the constructor is expected to throw an IllegalArgumentException.

    // This class tests three invalid parameters, as listed in requirement:
    // 1. the dew point temperature in Celsius which cannot be greater than the air temperature
    // 2. non-negative wind speed in miles per hour
    // 3. non-negative total rain received in millimeters

    // TODO:
    // The following possible invalid parameter combinations are not covered, as they are not listed in the requirement:
    // 1. Input values leading to a off-range relative humidity value (less than 0 or greater than 100)
    // 2. Input values possibly leading to a overflow or underflow of the calculated heat index or wind chill

    @Before
    public void setUp() throws Exception {
    }

    // Test 1: dew point temperature is greater than air temperature
    @Test(expected = IllegalArgumentException.class)
    public void parametersTest1() {
        WeatherReading wr1 = new WeatherReading(10, 15, 10, 5);
    }

    // Test 2: wind speed is negative
    @Test(expected = IllegalArgumentException.class)
    public void parametersTest2() {
        WeatherReading wr2 = new WeatherReading(10, 5, -1, 5);
    }

    // Test 3: total rain is negative
    @Test(expected = IllegalArgumentException.class)
    public void parametersTest3() {
        WeatherReading wr3 = new WeatherReading(10, 5, 10, -1);
    }
}
