# Weather

Often when you go outside, it feels much warmer or colder than the weatherman predicted. This is not a conspiracy. It is simply that the predicted temperature is not always what the real world feels like. Most weather channels and meteorology apps have started using this term to give folks a better understanding of how they will actually feel out in their environment. They do this by collecting readings from Stevenson Screen shelters.

A Stevenson Screen is a standard shelter that protects meteorological instruments which measure things like temperature, dew point, wind speed, rain, etc. These readings are used to calculate the different temperatures that appear in your weather report. For instance:

- **Dew point** is the temperature at below which water droplets (or dew) begin to form. The relationship between dew point (D), temperature in degrees Celsius (T), and relative humidity in percentage between 0 and 100 (R) is expressed in the following formula:

  $$
  D = T - \frac{100-R}{5}
  $$

- **Heat index** is a measure of how hot it feels when relative humidity is factored in with the actual temperature. It is calculated in a number of different ways but for our example, consider the formula for heat index (HI):

  $$
  HI = c_1 + C_2T + c_3R + c_4TR + c_5T^2 + c_6R^2 + c_7T^2R + c_8TR^2 + c_9T^2R^2
  $$

  where T is the temperature in degrees Celsius, R is the relative humidity in percent, and the coefficients are: `c1 = -8.78469475556`, `c2 = 1.61139411`, `c3 = 2.33854883889`, `c4 = -0.14611605`, `c5 = -0.012308094`, `c6 = -0.0164248277778`, `c7 = 0.002211732`, `c8 = 0.00072546`, and `c9 = -0.000003582`.

- **Wind chill** is related to heat index and is used when the real-feel temperature is lower than the actual temperature. There is some variation on how it is calculated depending on where you are but here in the United States, it is calculated with this formula:

where WC is the wind chill based on the air temperature in degrees Fahrenheit (T) and the wind speed in miles per hour (v).

## What to do

Package: `weather`

Design and implement a class called `WeatherReading` that represents a single reading of a weather station in a Stevenson Station. Your constructor takes four parameters: the air temperature in Celsius, the dew point temperature in Celsius which cannot be greater than the air temperature, the non-negative wind speed in miles per hour, and the non-negative total rain received in millimeters.

Methods that get different values from an object are called accessor methods or getters because they are named starting with "get". Your implementation should include each of the following:

> Tom's note:
> `getHeatIndex` and `getWindChill` return double, others return int

- getTemperature
- getDewPoint
- getWindSpeed
- getTotalRain
- getRelativeHumidity
- getHeatIndex
- getWindChill

Do not forget that every well-written class in Java should also include a toString method. Your implementation should create an output like this one:

`Reading: T = 23, D = 12, v = 3, rain = 12`

## Testing

Whenever you write a class, you should also write tests for that class that proves not only that your code CAN work but that it WILL ALWAYS work. Your goal here is to write tests to achieve as close to 100% coverage as possible. But even more importantly, **_your tests should be sufficient to convince someone else that your code works correctly_**.
