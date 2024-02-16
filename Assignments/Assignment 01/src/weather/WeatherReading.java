package weather;

public class WeatherReading {
    // air temperature reading (Celsius)
    private int temperature;
    // dew point temperature (Celsius)
    private int dewPoint;
    // wind speed reading (mph)
    private int windSpeed;
    // precipitation received (mm)
    private int totalRain;

    // The following attributes are calculated upon Instantiation
    // rH (percentage)
    private int relativeHumidity;
    private double heatIndex;
    private double windChill;

    public WeatherReading(int temperature, int dewPoint, int windSpeed, int totalRain) {
        // checking input values
        if (dewPoint > temperature) {
            System.out.println("Dew point temperature is greater than air temperature. Check input values.");
            throw new IllegalArgumentException();
        }

        if (windSpeed < 0) {
            System.out.println("Wind speed cannot be negative. Check input values.");
            throw new IllegalArgumentException();
        }

        if (totalRain < 0) {
            System.out.println("Total rain cannot be negative. Check input values.");
            throw new IllegalArgumentException();
        }

        this.temperature = temperature;
        this.dewPoint = dewPoint;
        this.windSpeed = windSpeed;
        this.totalRain = totalRain;

        // the calculation process is abstracted into methods
        // for re-usability, should the attributes to be updated in future requirements
        this.calculateRelativeHumidity();
        this.calculateHeatIndex();
        this.calculateWindChill();
    }

    @Override
    public String toString() {
        return "Reading: T = " + this.temperature + ", " +
                "D = " + this.dewPoint + ", " +
                "v = " + this.windSpeed + ", " +
                "rain = " + this.totalRain;
    }

    private void calculateRelativeHumidity() {
        this.relativeHumidity = 100 - 5 * (this.temperature - this.dewPoint);

        // NOTE:
        // requirement did not specify what to do if the calculated relative humidity is off-range
        // for current implementation, only a warning is printed to the console
        if (this.relativeHumidity < 0) {
            System.out.println("Calculated relative humidity is less than 0. Check input values.");
        }
        else if (this.relativeHumidity > 100) {
            System.out.println("Calculated relative humidity is greater than 100. Check input values.");
        }
    }

    private void calculateHeatIndex() {
        // coefficients stored in an array for readability & ease of modification
        double[] heatIndexCoefficient = {
                -8.78469475556,
                1.61139411,
                2.33854883889,
                -0.14611605,
                -0.012308094,
                -0.0164248277778,
                0.002211732,
                0.00072546,
                -0.000003582
        };
        this.heatIndex = heatIndexCoefficient[0]
                + heatIndexCoefficient[1] * this.temperature
                + heatIndexCoefficient[2] * this.relativeHumidity
                + heatIndexCoefficient[3] * this.temperature * this.relativeHumidity
                + heatIndexCoefficient[4] * Math.pow(this.temperature, 2)
                + heatIndexCoefficient[5] * Math.pow(this.relativeHumidity, 2)
                + heatIndexCoefficient[6] * Math.pow(this.temperature, 2) * this.relativeHumidity
                + heatIndexCoefficient[7] * this.temperature * Math.pow(this.relativeHumidity, 2)
                + heatIndexCoefficient[8] * Math.pow(this.temperature, 2) * Math.pow(this.relativeHumidity, 2);
    }

    private void calculateWindChill() {
        // the formula of wind chill requires temperature in Fahrenheit
        double temperatureF = ((double) this.temperature * 9 / 5) + 32;

        // coefficients stored in an array for readability & ease of modification
        double[] windChillCoefficient = {
                35.74,
                0.6215,
                -35.75,
                0.4275
        };
        this.windChill = windChillCoefficient[0]
                + windChillCoefficient[1] * temperatureF
                + windChillCoefficient[2] * Math.pow(this.windSpeed, 0.16)
                + windChillCoefficient[3] * temperatureF * Math.pow(this.windSpeed, 0.16);
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getDewPoint() {
        return this.dewPoint;
    }

    public int getWindSpeed() {
        return this.windSpeed;
    }

    public int getTotalRain() {
        return this.totalRain;
    }

    public int getRelativeHumidity() {
        return this.relativeHumidity;
    }

    public double getHeatIndex() {
        return this.heatIndex;
    }

    public double getWindChill() { return this.windChill; }
}
