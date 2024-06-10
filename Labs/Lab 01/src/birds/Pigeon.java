package birds;

import utils.BirdFood;

public class Pigeon extends Bird{
    public Pigeon(String name, String speciesName, String characteristics, int numberOfWings, BirdFood[] preferredFood, boolean isInstinct) {
        super(name, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
    }
}
