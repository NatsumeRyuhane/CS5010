package birds;

import utils.BirdFood;

public class FlightlessBird extends Bird {
    public FlightlessBird(String name, String speciesName, String characteristics, int numberOfWings, BirdFood[] preferredFood, boolean isInstinct) {
        super(name, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
        if (numberOfWings != 0) {
            throw new IllegalArgumentException("Flightless birds have no wings");
        }
    }
}
