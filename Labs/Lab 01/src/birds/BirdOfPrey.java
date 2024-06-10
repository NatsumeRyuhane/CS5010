package birds;

import utils.BirdFood;

public class BirdOfPrey extends Bird {
    public BirdOfPrey(String name, String speciesName, String characteristics, int numberOfWings, BirdFood[] preferredFood, boolean isInstinct) {
        super(name, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
    }
}
